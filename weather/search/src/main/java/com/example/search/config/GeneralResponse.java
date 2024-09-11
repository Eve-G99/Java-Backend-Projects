package com.example.search.config;

public class GeneralResponse {
    private String code;
    private String timestamp;
    private String data;

    public GeneralResponse(String code, String timestamp, String data) {
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public GeneralResponse() {
    }
}
