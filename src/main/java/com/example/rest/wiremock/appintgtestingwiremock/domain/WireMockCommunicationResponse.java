package com.example.rest.wiremock.appintgtestingwiremock.domain;

import org.springframework.http.HttpStatus;

public class WireMockCommunicationResponse {
    private HttpStatus status;
    private String responseBody;

    public WireMockCommunicationResponse(HttpStatus status, String responseBody) {
        this.status = status;
        this.responseBody = responseBody;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getResponseBody() {
        return responseBody;
    }

    @Override
    public String toString() {
        return "WireMockCommunicationResponse{" +
                "status=" + status +
                ", responseBody='" + responseBody + '\'' +
                '}';
    }
}
