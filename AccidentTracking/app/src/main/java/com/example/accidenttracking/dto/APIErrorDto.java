package com.example.accidenttracking.dto;

public class APIErrorDto {
    private String HTTPStatus;
    private String reason;
    private String errorMessage;
    private int statusCode;

    public APIErrorDto(){}
    public APIErrorDto(String HTTPStatus, String reason, String errorMessage, int statusCode) {
        this.HTTPStatus = HTTPStatus;
        this.reason = reason;
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getHTTPStatus() {
        return HTTPStatus;
    }

    public void setHTTPStatus(String HTTPStatus) {
        this.HTTPStatus = HTTPStatus;
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
