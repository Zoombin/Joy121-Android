package com.joy.json.exception;

public class JsonException extends Exception {
    private static final long serialVersionUID = 1L;
    
    private String mExtra;

    public JsonException(String message) {
        super(message);
    }

    public JsonException(String message, String extra) {
        super(message);
        mExtra = extra;
    }
    
    public String getExtra() {
        return mExtra;
    }
}
