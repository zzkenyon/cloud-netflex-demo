package com.pd.user;

import com.pd.result.RequestResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/30 15:45
 */
@SpringBootApplication
@RestController
public class SsoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class,args);
    }

    @GetMapping(value = "verify")
    public RequestResult verify(@RequestParam("accessToken")String token){

        return new RequestResult.Builder().Ok(Boolean.TRUE);
    }
}
