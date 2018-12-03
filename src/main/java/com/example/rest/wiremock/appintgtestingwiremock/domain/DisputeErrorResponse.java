package com.example.rest.wiremock.appintgtestingwiremock.domain;

public class DisputeErrorResponse {
    private Integer statusCode;
    private Integer errorId;
    private String message;

    public Integer getStatusCode() {
        return statusCode;
    }

    public Integer getErrorId() {
        return errorId;
    }

    public String getMessage() {
        return message;
    }

    public DisputeErrorResponse(Integer statusCode, Integer errorId, String message) {

        this.statusCode = statusCode;
        this.errorId = errorId;
        this.message = message;
    }

    public DisputeErrorResponse() {
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setErrorId(Integer errorId) {
        this.errorId = errorId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
