package com.example.rest.wiremock.appintgtestingwiremock.domain;

public class WireMockMappingResponse {
    private String status;
    private String body;
    private String headers;

    public WireMockMappingResponse(String status, String body, String headers) {
        this.status = status;
        this.body = body;
        this.headers = headers;
    }



    public String getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public String getHeaders() {
        return headers;
    }
}
