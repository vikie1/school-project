package com.example.accidenttracking.dto;

public class APIErrorDto {
    private String status;
    private String reason;
    private String errorMessage;
    private int statusCode;

    public APIErrorDto(){}
    public APIErrorDto(String HTTPStatus, String reason, String errorMessage, int statusCode) {
        this.status = HTTPStatus;
        this.reason = reason;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String HTTPStatus) {
        this.status = HTTPStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
