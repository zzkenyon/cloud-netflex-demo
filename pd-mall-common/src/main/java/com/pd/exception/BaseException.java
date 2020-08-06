package com.pd.exception;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/6 09:22
 */
public class BaseException extends RuntimeException{

    private int code;
    private String message;

    public BaseException(String message){
        super(message);
        this.message = message;
    }

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException() {
        super();
    }
}
