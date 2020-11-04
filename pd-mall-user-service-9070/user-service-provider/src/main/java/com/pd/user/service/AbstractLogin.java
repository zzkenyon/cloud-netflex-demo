package com.pd.user.service;

import com.pd.exception.BizException;
import com.pd.result.RequestResult;
import com.pd.user.dto.AuthLoginDto;
import com.pd.user.mapper.entity.MemberPo;
import com.pd.user.utils.JwtGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/11/4 09:50
 */
@Slf4j
public abstract class AbstractLogin implements Login{
    public static ConcurrentHashMap<Integer,AbstractLogin> loginMapper = new ConcurrentHashMap<>();
    
    @PostConstruct
    public void init(){
        loginMapper.put(getLoginType(),this);
    }

    @Override
    public RequestResult doLogin(AuthLoginDto authLoginDto) throws BizException {
        log.info("begin AbstractLogin.doLogin:"+authLoginDto);
        validate(authLoginDto);
        MemberPo memberPo = doProcessor(authLoginDto);
        Map<String,Object> payLoad = new HashMap<>();
        payLoad.put("uid",memberPo.getId());
        payLoad.put("exp", DateTime.now().plusHours(1).toDate().getTime()/1000);
        String token = JwtGeneratorUtil.generateToken(payLoad);
        return new RequestResult.Builder().data(token).Ok();
    }

    protected abstract Integer getLoginType();

    /**
     * 通过子类去完成验证
     * @param authLoginDto
     */
    public abstract void validate(AuthLoginDto authLoginDto);

    /**
     * 登录校验
     * @param authLoginDto
     */
    public abstract MemberPo doProcessor(AuthLoginDto authLoginDto);

}
