package com.water.photo.controller;

import com.guanweiming.common.ServerResponse;
import com.water.photo.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chezhu.xin
 */
@Slf4j
@Api(description = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ServerResponse<String> login(@RequestParam String id, String remark){
        return userService.login(id,remark);
    }
}
