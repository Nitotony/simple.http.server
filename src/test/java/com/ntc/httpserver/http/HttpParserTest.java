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
        } catch (HttpParsingException e) {
            fail(e);
        }
        assertNotNull(request);
        assertEquals(HttpMethod.GET, request.getMethod());
        assertEquals(request.getBestCompatableVersion(),HttpVersion.HTTP_1_1);

        assertEquals("/login", request.getRequestTarget());

    }


    @Test
    void parseHttpsRequestBad() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCase()
            );
            fail();
        } catch (HttpParsingException e) {
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
        } catch (HttpParsingException e) {
            assertEquals(e.getMessage(),HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED.MESSAGE);



        }}

    @Test
    void parseHttpsRequestBad_Request_Line_descripancy() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCase_Invalid_num_items()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getMessage(),HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);



        }}

    @Test
    void parseHttpsRequestBad_Request_Line_Empty() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCase_Empty_RequestLine()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getMessage(),HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);



        }}

    @Test
    void parseHttpsRequestBad_RequestLine_NoLFonlyCF() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadTestCase_RequestLine_NoLFonlyCF()
            );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getMessage(),HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);



        }}
    @Test
    void parseHttpsRequestBadHttpVersion() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateBadHttpVersionTestCase()          );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getMessage(),HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);



        }}
@Test
    void parseHttpsRequestUnsuppotedHttpVersion() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateUnsupportedHttpVersionTestCase()          );
            fail();
        } catch (HttpParsingException e) {
            assertEquals(e.getMessage(),HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED.MESSAGE);



        }}
    @Test
    void parseHttpsRequestSupportedHttpVersion() {
        HttpRequest request= null;
        try {
            request = httpParser.parseHttpRequest(
                    generateSupportedHttpVersionTestCase()          );
            assertNotNull(request);
            assertEquals(request.getBestCompatableVersion(), HttpVersion.HTTP_1_1);

        } catch (HttpParsingException e) {
            fail();



        }}
    private InputStream generateSupportedHttpVersionTestCase() {
        String rawData="GET /login HTTP/1.2\r\n" +"\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

    private InputStream generateUnsupportedHttpVersionTestCase() {
        String rawData="GET /login HTTP/2.1\r\n" +"\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }


    private InputStream generateBadTestCase_Invalid_num_items() {

        String rawData="GET /dsafsa /login HTTP/1.1\r\n" +"\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
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
    private InputStream generateBadTestCase_Empty_RequestLine() {

        String rawData="\r\n" +"\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
    private InputStream generateBadTestCase_RequestLine_NoLFonlyCF() {
        String rawData="GET /login HTTP/1.1\r" +
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
    private InputStream generateBadHttpVersionTestCase() {

        String rawData="GET /login HTT/1.1\r\n" +"\r\n";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }
}