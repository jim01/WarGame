package com.jameshackett.war2022.core;


public class PayloadResponse<T> extends Response {

    public T data;

    public PayloadResponse() {
        this(null);
    }


    public PayloadResponse(T data) {
        super(STATUS_OK);
        this.data = data;
    }

    public PayloadResponse(int status, T data){
        super(status);
        this.data = data;
    }

    public PayloadResponse(int status, T data, String message) {
        this(status, data);
        this.message = message;
    }

    @Override
    public String toString() {
        return "status="+status+" data=\""+data+"\" message=\""+message+"\"";
    }

    public final static PayloadResponse OK = new PayloadResponse(null);
}
