package com.pd.exception;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/6 09:26
 */
public class ValidException extends BaseException{
    public ValidException(){
        super();
    }

    public ValidException(String message) {
        super(message);
    }

    public ValidException(int code, String message) {
        super(code, message);
    }
}
