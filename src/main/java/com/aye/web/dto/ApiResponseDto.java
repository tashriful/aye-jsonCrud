package com.aye.web.dto;

public class ApiResponseDto<T>{
    private int status;
    private String message;
    private T responseData;
    private String timestamp;

    public ApiResponseDto() {

    }

    public ApiResponseDto(int status, String message, T responseData) {
        this.status = status;
        this.message = message;
        this.responseData = responseData;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
