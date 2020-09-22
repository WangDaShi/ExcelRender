package com.loatr.excel.exception;

/**
 * json解析异常
 */
public class JsonParseException extends RuntimeException{

    public JsonParseException(){

    }

    public JsonParseException(String message){
        super(message);
    }
}
