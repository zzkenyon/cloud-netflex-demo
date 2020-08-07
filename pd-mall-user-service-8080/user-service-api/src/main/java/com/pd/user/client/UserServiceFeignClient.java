package com.pd.user.client;

import com.pd.user.dto.UserService;
import com.pd.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@FeignClient(value = "user-service",fallback = UserServiceFeignClient.UserServiceFallBack.class)
public interface UserServiceFeignClient extends UserService {

    @Component
    public class UserServiceFallBack implements UserServiceFeignClient{

        public User queryUserById(int id) {
            return null;
        }

        public Integer insertUsers(List<User> users) {
            return null;
        }
    }

}
