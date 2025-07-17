package com.ntc.httpserver.http;

public class HttpRequest extends HttpMessage{



    private HttpMethod method;
    private String requestTarget;
    private String originalhttpVersion;

    public HttpVersion getBestCompatableVersion() {
        return bestCompatableVersion;
    }

    private HttpVersion bestCompatableVersion;

    HttpRequest(){

    }
    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String methodname) throws HttpParsingException {
        for (HttpMethod method : HttpMethod.values()) {
            if(methodname.equals(method.name())) {
                this.method = method;
                return;
            }
        }
        throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);


    }

    void setRequestTarget(String string) throws HttpParsingException {
        if(string.isEmpty()){
            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
        }
        this.requestTarget = string;
    }
    String getRequestTarget() {
        return requestTarget;
    }

    void setHttpVersion(String originalHttpVersion) throws HttpParsingException, BadHttpVersionException {
        this.originalhttpVersion = originalHttpVersion;
        this.bestCompatableVersion = HttpVersion.getBestCompatibleVersion(originalHttpVersion);

        if(this.bestCompatableVersion == null) {
            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED);
        }

     }
}
