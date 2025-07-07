package com.ntc.httpserver.http;

import com.ntc.httpserver.config.HttpConfigurationException;

public class HttpRequest extends HttpMessage{



    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest(){

    }
    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String methodname) throws HttpPassingException {
        for (HttpMethod method : HttpMethod.values()) {
            if(methodname.equals(method.name())) {
                this.method = method;
                return;
            }
        }
        throw new HttpPassingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);


    }
}
