package com.ntc.httpserver.http;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpHeadersParserTest {
    private  HttpParser httpParser;
    private Method parseHeaderMethod;

    @BeforeAll
    public void beforeClass() throws NoSuchMethodException {
        httpParser = new HttpParser();
        Class<HttpParser> cls=HttpParser.class;
        parseHeaderMethod=cls.getDeclaredMethod("parseHeaders", InputStreamReader.class, HttpRequest.class);
        parseHeaderMethod.setAccessible(true);
    }
    @Test
    public void testSimpleSingleHeader() throws InvocationTargetException, IllegalAccessException {
    HttpRequest request = new HttpRequest();
    InputStream inputStream = generateSimpleSingleHeaderMessage();
    InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
    parseHeaderMethod.invoke(httpParser, reader, request);
}

    private InputStream  generateSimpleSingleHeaderMessage(){
        String rawdata="Host: localhost:8080\r\n";

        InputStream inputStream=new ByteArrayInputStream(rawdata.getBytes(StandardCharsets.US_ASCII));
       return inputStream;
    }



}
