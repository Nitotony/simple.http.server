package com.ntc.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {
    private Socket socket;
    private  final static Logger logger = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;

    }
@Override
    public void run() {
    InputStream inputStream= null;
    OutputStream outputStream=null;
    try {
        inputStream= socket.getInputStream();
        outputStream= socket.getOutputStream();

        // we do reading
        int _byte;
        while((_byte=inputStream.read())>=0){
            System.out.print((char)_byte);
        }



        // we do writing

        String html="<html><head><title>Simple HttpServer</title></head><body><h1>page served using http server made using JAVA</h1></body></html>";
        final String CRLF = "\r\n";// ascii 13,10
        String response="HTTP/1.1 200 OK\n\r"+CRLF+"Content-Length: "+html.getBytes().length+CRLF+CRLF+html+CRLF+CRLF;
        outputStream.write(response.getBytes());


        logger.info("Processing is finished");
    } catch (IOException e) {
        logger.info("Failed to process http request",e);


    }
    finally {
        if (inputStream != null) {

            try {
                inputStream.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        if (outputStream != null) {
            try{
                outputStream.close();
            }
            catch(IOException e){

            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


}

}
