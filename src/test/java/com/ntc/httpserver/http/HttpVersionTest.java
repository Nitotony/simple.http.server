package com.ntc.httpserver.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HttpVersionTest {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HttpVersionTest.class);

    @Test
    void getBestCompatableVersionExactMatch() {
        HttpVersion httpVersion =null;
        try {
            httpVersion = HttpVersion.getBestCompatibleVersion("HTTP/1.1");
            assertNotNull(httpVersion);
            assertEquals(HttpVersion.HTTP_1_1, httpVersion);
        } catch (BadHttpVersionException | HttpParsingException e) {
            e.printStackTrace();
           fail();

        }


    }


    @Test
    void getBestCompatableVersionBadMatch() {
        HttpVersion httpVersion =null;
        try {
            httpVersion = HttpVersion.getBestCompatibleVersion("http/1.1");
            fail();
        } catch (BadHttpVersionException | HttpParsingException e) {



        }

    }

    @Test
    void getBestCompatableVersionHigherMatch() {
        HttpVersion httpVersion =null;
        try {
            httpVersion = HttpVersion.getBestCompatibleVersion("HTTP/1.2");
            assertNotNull(httpVersion);
            assertEquals(HttpVersion.HTTP_1_1, httpVersion);
        } catch (BadHttpVersionException | HttpParsingException e) {

            fail();

        }


    }





}

