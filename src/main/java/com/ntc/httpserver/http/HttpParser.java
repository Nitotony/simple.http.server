package com.ntc.httpserver.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpParser {
    private final static Logger logger = LoggerFactory.getLogger(HttpParser.class);
    public  void parseHttpRequest(InputStream inputStream) {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        parseRequestLine();
        parseHeaders();
        parseBody();
    }
}
