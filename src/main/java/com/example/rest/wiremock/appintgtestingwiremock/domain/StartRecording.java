package com.example.rest.wiremock.appintgtestingwiremock.domain;

public class StartRecording {
    private String targetBaseUrl;

    public StartRecording(String targetBaseUrl) {
        this.targetBaseUrl = targetBaseUrl;
    }

    public String getTargetBaseUrl() {
        return targetBaseUrl;
    }

    @Override
    public String toString() {
        return "StartRecording{" +
                "targetBaseUrl='" + targetBaseUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StartRecording that = (StartRecording) o;

        return getTargetBaseUrl() != null ? getTargetBaseUrl().equals(that.getTargetBaseUrl()) : that.getTargetBaseUrl() == null;
    }

    @Override
    public int hashCode() {
        return getTargetBaseUrl() != null ? getTargetBaseUrl().hashCode() : 0;
    }
}
