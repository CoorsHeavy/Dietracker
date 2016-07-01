package com.hart.samplehartapplication.retrofit;

public class RestIntercepterErrorEvent extends BaseEvent{
    String code;
    String message;
    public RestIntercepterErrorEvent(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public String getCode() {
        return code;
    }
}
