package com.jameshackett.war2022.core;

public class Response {

    //success
    public static final int STATUS_OK                   = 0;
    public static final int STATUS_NOT_OK               = 1; //equivalent of boolean false

    //errors
    public static final int STATUS_MISSING_PARAM        = 100;
    public static final int STATUS_INTERNAL_ERROR       = 103;
    public static final int STATUS_INVALID_ACCOUNT      = 104;
    public static final int STATUS_INVALID_STATE        = 105;
    public static final int STATUS_NOT_FOUND            = 106;
    public static final int STATUS_ALREADY_EXISTS       = 107;
    public static final int STATUS_INVALID_PARAM        = 108;
    public static final int STATUS_NOT_AUTHENTICATED    = 109;
    public static final int STATUS_NOT_ALLOWED          = 110;
    public static final int STATUS_SUSPENDED            = 114;
    public static final int STATUS_BAD_CREDENTIALS      = 115;

    public static final Response OK = new Response(STATUS_OK);
    public static final Response NOT_OK = new Response(STATUS_NOT_OK);

    public int status = STATUS_OK;
    public long timestamp = System.currentTimeMillis(); //server time
    public String message;

    public Response() {
    }

    public Response(int status) {
        this.status = status;
    }

    public boolean isOK() {
        return status == STATUS_OK;
    }

    @Override
    public String toString() {
        return "status="+status;
    }

}
