package com.pd.user.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaozhengkang
 * @description 用于创建和解析jwt的工具类
 * @date 2020/11/3 22:33
 */
@Slf4j
public class JwtGeneratorUtil {
    private static String SECRET_KEY = "zhaopanda";

    private static Key getKeyInstance(){
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        byte[] keySignature  = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key key = new SecretKeySpec(keySignature,algorithm.getJcaName());
        return key;
    }

    /**
     * 生成token
     * @param payLoad
     * @return
     */
    public static String generateToken(Map<String,Object> payLoad){
        ObjectMapper objectMapper = new ObjectMapper();
        String token = null;
        try {
            token = Jwts.builder().setPayload(objectMapper.writeValueAsString(payLoad))
                    .signWith(SignatureAlgorithm.HS256,getKeyInstance()).compact();
        } catch (JsonProcessingException e) {
            log.error("generateToken: " + e);
        }
        return token;
    }

    /**
     * token 解析
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        return claimsJws.getBody();
    }


    public static void main(String[] args) {
        Map<String,Object> payload = new HashMap<>();
        payload.put("uid","zhaozhengkang");
        payload.put("role","admin");
        String token = generateToken(payload);
        System.out.println(token);
        Claims claims = parseToken(token);
        System.out.println(claims);
        String uid = claims.get("uid",String.class);
        System.out.println(uid);
    }
}
