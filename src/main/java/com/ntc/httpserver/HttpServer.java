package com.ntc.httpserver;

import com.ntc.httpserver.config.Configuration;
import com.ntc.httpserver.config.ConfigurationManager;
import com.ntc.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//Driver classs for the Http Server
public class HttpServer {
    private  final static Logger logger = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        logger.info("HTTP Server");

        ConfigurationManager.getInstance().loadConfigurationFile("C:\\Users\\nitot\\IdeaProjects\\simple.http.server\\src\\main\\resources\\http.json");
        Configuration conf=ConfigurationManager.getInstance().getMyCurrentConfiguration();
        logger.info("Using Port"+conf.getPort());
        logger.info("Using web Root "+conf.getWebRoot());


        try {
            ServerListenerThread serverListenerThread=new ServerListenerThread(conf.getPort(), conf.getWebRoot());
            serverListenerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
 }
