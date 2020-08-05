package com.pd.goods.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.pd.goods.exception.ValidException;
import com.pd.result.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/5 16:01
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    public RequestResult handle(Exception e, HttpServletRequest request){
        log.info("GlobalExceptionHandler.handleException:{},{}",request.getRequestURI(),e);
        String msg="系统繁忙："+e.getMessage();
        if(e instanceof ValidException){
            msg=e.getMessage();
        }
        return new RequestResult.Builder().buildCustomize(msg);
    }
}
