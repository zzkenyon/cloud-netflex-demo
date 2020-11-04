package com.pd.user.controller;

import com.pd.exception.BizException;
import com.pd.result.RequestResult;
import com.pd.user.dto.AuthLoginDto;
import com.pd.user.service.AbstractLogin;
import com.pd.user.service.Login;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/11/4 10:19
 */
@RestController
public class LoginController {

    @PostMapping("/login")
    public RequestResult loginAuth(@RequestBody @Validated AuthLoginDto authLoginDto, BindingResult bindingResult){
        authLoginDto.validData(bindingResult);
        //登录逻辑?
        Login login= AbstractLogin.loginMapper.get(authLoginDto.getLoginType());
        if(login==null){
            throw new BizException("暂不支持该种登录类型");
        }
        return login.doLogin(authLoginDto);
    }

    @GetMapping(value = "verify")
    public RequestResult verify(@RequestParam("accessToken")String token){

        return new RequestResult.Builder().Ok(Boolean.TRUE);
    }
}
