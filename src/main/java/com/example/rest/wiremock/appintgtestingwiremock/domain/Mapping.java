package com.example.rest.wiremock.appintgtestingwiremock.domain;

public class Mapping {
    private String id;
    private String name;
    private WireMockMappingRequest request;
    private WireMockMappingResponse response;
    private String uuid;
    private Boolean persistent = Boolean.FALSE;

    public Mapping(String id, String name, WireMockMappingRequest request, WireMockMappingResponse response, String uuid, Boolean persistent) {
        this.id = id;
        this.name = name;
        this.request = request;
        this.response = response;
        this.uuid = uuid;
        this.persistent = persistent;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WireMockMappingRequest getRequest() {
        return request;
    }

    public WireMockMappingResponse getResponse() {
        return response;
    }

    public String getUuid() {
        return uuid;
    }

    public Boolean getPersistent() {
        return persistent;
    }
}
