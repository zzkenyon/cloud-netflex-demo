package com.pd.gateway.filter.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/29 17:33
 */
/**
 * 用于登录验证的token，由SSO服务器签发
 * 因此要校验是否有效，必须将token发送给SSO服务器，由SSO来校验
 */
@Data
@Slf4j
public class AccessToken {
    private AccessTokenValidator validator;
    private String tokenStr;
    public AccessToken(String tokenStr){
        validator = new AccessTokenValidator();
        this.tokenStr = tokenStr;
        // 构造的时候发起请求
        validator.syncRequestSso(tokenStr);
    }

    public boolean isValid(){
        if(validator.valid!=null){
            return validator.valid;
        }
        else {
            return false;
        }
    }

    /**
     * 需要添加token携带信息的 解析方法
     */


    static class AccessTokenValidator implements TokenValidator{
        private final OkHttpClient okHttpClient = new OkHttpClient();
        private Boolean valid;

        public AccessTokenValidator(){
        }
        /**
         * 同步请求
         * @param token
         */
        public void syncRequestSso(String token) {
            Request request = new Request.Builder()
                    .get()
                    .url("http://localhost:8081/verify?accessToken=" + token)
                    .build();

            Call call = okHttpClient.newCall(request);
            Response response = null;
            try {
                response = call.execute();
                JSONObject jb = JSON.parseObject(response.body().toString());
                if(jb.getBoolean("data")!=null)
                    valid = jb.getBoolean("data");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 异步请求SSO服务器验证token是否合法
         * @param token
         * @return
         */
        public void asyncRequestSso(String token) {
            Request request = new Request.Builder()
                    .get()
                    .url("http://localhost:8081/verify?accessToken=" + token)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new RuntimeException("Unexpected code " + response);
                    } else {
                        JSONObject jb = JSON.parseObject(response.body().toString());
                        if(jb.getBoolean("data")!=null)
                            valid = jb.getBoolean("data");
                    }
                }
            });
        }


    }
}
