package com.pd.user.enums;

/**
 * 登录类型枚举类
 */
public enum LoginTypeEnum {
    NORMAL(0,"账号密码登录"),
    PHONE_PWD(1,"手机密码登录"),
    PHONE_CODE(2,"手机验证码登录"),
    WECHAT(3,"微信登录");

    private int code;
    private String memo;

    LoginTypeEnum(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
