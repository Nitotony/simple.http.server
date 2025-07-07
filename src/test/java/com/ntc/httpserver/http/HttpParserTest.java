package com.ntc.httpserver.http;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HttpParserTest {
    private HttpParser httpParser ;
    @BeforeAll
    public void beforeClass(){
        httpParser = new HttpParser();
    }
    @Test
    void parseHttpRequestGood() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateValidTestCase()
            );
        } catch (HttpPassingException e) {
            fail(e);
        }

        assertEquals(request.getMethod(),HttpMethod.GET);
    }


    @Test
    void parseHttpsRequestBad() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCase()
            );
            fail();
        } catch (HttpPassingException e) {
            assertEquals(e.getMessage(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED.MESSAGE);





    }}

    @Test
    void parseHttpsRequestBad_Method_Len_Large() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCase_Method_Len_Large()
            );
            fail();
        } catch (HttpPassingException e) {
            assertEquals(e.getMessage(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED.MESSAGE);



        }}



    private InputStream generateBadTestCase() {

        String rawData="GeT /login HTTP/1.1\r\n" +"\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
    private InputStream generateBadTestCase_Method_Len_Large() {

        String rawData="GTERFSDSDGSDG /login HTTP/1.1\r\n" +"\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
    private InputStream generateValidTestCase(){
        String rawData="GET /login HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "sec-ch-ua: \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Google Chrome\";v=\"138\"\r\n" +
                "sec-ch-ua-mobile: ?0\r\n" +
                "sec-ch-ua-platform: \"Windows\"\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Sec-Fetch-Dest: document\r\n" +
                "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                "Accept-Language: en-US,en-IN;q=0.9,en;q=0.8,ar;q=0.7,ml;q=0.6\r\n" +
                "l;q=0.6\r\n"+
                "\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

}