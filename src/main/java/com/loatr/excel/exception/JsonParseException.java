package com.loatr.excel.exception;

/**
 * json解析异常，先写出来，暂时不使用
 */
public class JsonParseException extends RuntimeException{

    public JsonParseException(){

    }

    public JsonParseException(String message){
        super(message);
    }
}
