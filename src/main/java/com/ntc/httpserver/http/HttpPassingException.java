package com.ntc.httpserver.http;

public class HttpPassingException  extends Exception {
    private final HttpStatusCode errorCode;

    public HttpPassingException(HttpStatusCode errorCode) {
        super(errorCode.MESSAGE);
        this.errorCode = errorCode;
    }
    public HttpStatusCode getErrorCode() {
        return errorCode;
    }
}
