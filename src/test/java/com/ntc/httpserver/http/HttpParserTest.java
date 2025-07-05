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
    void parseHttpRequest() {
        httpParser.parseHttpRequest(
                generateValidTestCase()
        );
    }

    private InputStream generateValidTestCase(){
        String rawData="GET /login HTTP/1.1\n\r" +
                "Host: localhost:8080\n\r" +
                "Connection: keep-alive\n\r" +
                "Cache-Control: max-age=0\n\r" +
                "sec-ch-ua: \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Google Chrome\";v=\"138\"\n\r" +
                "sec-ch-ua-mobile: ?0\n\r" +
                "sec-ch-ua-platform: \"Windows\"\n\r" +
                "Upgrade-Insecure-Requests: 1\n\r" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36\n\r" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\n\r" +
                "Sec-Fetch-Site: none\n\r" +
                "Sec-Fetch-Mode: navigate\n\r" +
                "Sec-Fetch-User: ?1\n\r" +
                "Sec-Fetch-Dest: document\n\r" +
                "Accept-Encoding: gzip, deflate, br, zstd\n\r" +
                "Accept-Language: en-US,en-IN;q=0.9,en;q=0.8,ar;q=0.7,ml;q=0.6\n\r" +
                "l;q=0.6\n\r"+
                "\n\r";
        InputStream inputStream = new ByteArrayInputStream(
                rawData.getBytes(
                        StandardCharsets.US_ASCII
                )
        );
        return inputStream;
    }

}