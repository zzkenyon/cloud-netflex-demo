package com.pd.user.controller;

import com.pd.client.IUserAuthFeignClient;
import com.pd.exception.BizException;
import com.pd.exception.ValidException;
import com.pd.result.RequestResult;
import com.pd.user.utils.JwtGeneratorUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/11/4 11:13
 */
@RestController
public class UserAuthFeignClient implements IUserAuthFeignClient {

    /**
     * GET "/token"
     * @param token
     * @return
     */
    @Override
    public RequestResult<String> validToken(String token) {
        if(StringUtils.isBlank(token)){
            throw new ValidException("token为空");
        }
        try{
            Claims claims = JwtGeneratorUtil.parseToken(token);
            return new RequestResult.Builder<String>().data(claims.get("uid").toString()).Ok();
        }catch (BizException e){
            return new RequestResult.Builder<String>().buildCustomize(401,e.getMessage());
        } catch (ExpiredJwtException e){
            return new RequestResult.Builder<String>().buildCustomize(401,"[user-service] token已经过期");
        }catch (SignatureException e){
            return new RequestResult.Builder<String>().buildCustomize(401,"[user-service] 签名校验失败");
        }
    }
}
