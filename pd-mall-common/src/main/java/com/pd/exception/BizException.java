package com.pd.exception;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/6 10:39
 */
public class BizException extends BaseException{

    public BizException(String message) {
        super(message);
    }

    public BizException(int code, String message) {
        super(code, message);
    }

    public BizException() {
    }
}
