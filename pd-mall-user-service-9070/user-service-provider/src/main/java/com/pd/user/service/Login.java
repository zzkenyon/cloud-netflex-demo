package com.pd.user.service;

import com.pd.exception.BizException;
import com.pd.result.RequestResult;
import com.pd.user.dto.AuthLoginDto;

public interface Login {
    RequestResult doLogin(AuthLoginDto authLoginDto) throws BizException;
}
