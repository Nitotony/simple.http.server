package com.ntc.httpserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private final static Logger logger = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP=0x20;//32
    private static final int CR=0x0D;//13
    private static final int LF=0x0A;//10

    public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParsingException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest request=new HttpRequest();
        try {
            parseRequestLine(reader,request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        parseHeaders(reader,request);
//        parseBody(reader,request);

        return request;
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {

    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {
        int _byte;
        while ((_byte= reader.read())>=0){
            if (_byte==CR){
                _byte=reader.read();
                if (_byte==LF){

                }
                else{
                    throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
            }
            else{

            }
        }
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {


        StringBuilder processingDataBuffer=new StringBuilder();

        boolean methodParsed=false;
        boolean requestTargetParsed=false;
        int _byte;
        while ((_byte = reader.read()) >= 0) {

            if(_byte==CR){
                _byte= reader.read();
                if(_byte==LF){
                    if(!methodParsed||!requestTargetParsed){
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                    try {
                        request.setHttpVersion(processingDataBuffer.toString());
                    } catch (BadHttpVersionException e) {
                        throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }
                    logger.debug("Request Line VERSION to Process : "+processingDataBuffer.toString());

                    return;
                }
                else {
                    throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
            }

            if(_byte==SP){
                //Todo proces previous data

                if(!methodParsed){

                    logger.debug("Request Line METHOD to Process : "+processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed=true;

                } else if (!requestTargetParsed) {
                    requestTargetParsed=true;
                    request.setRequestTarget(processingDataBuffer.toString());

                    logger.debug("Request Line TARGET to Process : "+processingDataBuffer.toString());
                }

                else{
                    throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }

                processingDataBuffer.delete(0,processingDataBuffer.length());

            }
            else {
                processingDataBuffer.append((char)_byte);

                if(!methodParsed){
                    if(processingDataBuffer.length()>HttpMethod.MAX_LENGTH){
                        throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
                    }
                }

            }

        }
    }
}
