package com.jameshackett.war2022.core;


public class ErrorResponse<T> extends PayloadResponse<T> {

    public ErrorResponse(int errorCode) {
        this(errorCode, null, false);
    }

    public ErrorResponse(int errorCode, String message) {
        this(errorCode, message, false);
    }

    public ErrorResponse(int errorCode, String message, boolean suppressLogging) {
        super(errorCode, null, message);
    }

    public ErrorResponse(int errorCode, T data, String message) {
        super(errorCode, data, message);
    }

    @Override
    public String toString() {
        return "status="+status+" message=\""+message+"\"";
    }

}
