package com.ntc.httpserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private final static Logger logger = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP=0x20;//32
    private static final int CR=0x0D;//13
    private static final int LF=0x0A;//10

    public  void parseHttpRequest(InputStream inputStream) {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest request=new HttpRequest();
        try {
            parseRequestLine(reader,request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        parseHeaders(reader,request);
        parseBody(reader,request);
    }

    private void parseBody(InputStreamReader reader, HttpRequest request) {

    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) {
    }

    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException {
        StringBuilder processingDataBuffer=new StringBuilder();
        int _byte;
        while ((_byte = reader.read()) >= 0) {

            if(_byte==CR){
                _byte= reader.read();
                if(_byte==LF){
                    logger.debug("Request Line to Process"+processingDataBuffer.toString());
                    return;
                }
            }

            if(_byte==SP){
                //Todo proces previous data
            }
            else {
                processingDataBuffer.append((char)_byte);

            }

        }
    }
}
