package com.pd.user.dto;

import com.pd.user.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserService {
    @GetMapping(value = "/user/{id}")
    User queryUserById(@PathVariable("id") int id);

    @PostMapping(value = "/user")
    Integer insertUsers(@RequestBody List<User> users);
}
