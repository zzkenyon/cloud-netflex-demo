package com.pd.user.controller;

import com.pd.user.dto.UserService;
import com.pd.user.model.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/22 11:08
 */
@RestController
public class UserController implements UserService {

    @Override
    public User queryUserById(int id) {
        User res = new User();
        res.setName("zhaozhengkang");
        res.setAge(29);
        return res;
    }

    @Override
    public Integer insertUsers(List<User> users) {
        return null;
    }
}
