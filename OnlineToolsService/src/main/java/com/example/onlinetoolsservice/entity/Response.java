package com.example.onlinetoolsservice.entity;

public class Response {
    private String chat_response;
    private int code;

    public void setChat_response(String chat_response) {
        this.chat_response = chat_response;
    }
    public String getChat_response() {
        return chat_response;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
