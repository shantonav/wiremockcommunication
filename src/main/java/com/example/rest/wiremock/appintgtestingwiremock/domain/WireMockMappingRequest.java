package com.example.rest.wiremock.appintgtestingwiremock.domain;

public class WireMockMappingRequest {
    private String url;
    private String method;

    public WireMockMappingRequest(String url, String method) {
        this.url = url;
        this.method = method;
    }



    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
