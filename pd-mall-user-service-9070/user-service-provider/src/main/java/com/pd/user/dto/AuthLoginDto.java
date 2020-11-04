package com.pd.user.dto;

import com.pd.exception.ValidException;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotNull;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/11/4 09:46
 */
@Data
public class AuthLoginDto {
    private String username;
    private String password;

    private String phone;
    private String code;

    private String openId;
    @NotNull(message = "登录类型不能为空")
    private Integer loginType;

    public void validData(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder=new StringBuilder();
            for(ObjectError oe:bindingResult.getAllErrors()){
                stringBuilder.append(oe.getDefaultMessage()+"\n");
            }
            throw new ValidException(stringBuilder.toString());
        }
    }
}
