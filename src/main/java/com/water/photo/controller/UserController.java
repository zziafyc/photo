package com.water.photo.controller;

import com.github.pagehelper.PageInfo;
import com.guanweiming.common.ServerResponse;
import com.water.photo.common.DataRequest;
import com.water.photo.common.DataResponse;
import com.water.photo.domain.User;
import com.water.photo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("用户数据")
    @GetMapping("user")
    public DataResponse<User> userList(DataRequest request) {
        PageInfo<User> page = userService.userList(request.getPage(), request.getSize());
        return new DataResponse<>(page, request.getDraw());
    }

    @PostMapping("delete")
    public ServerResponse<String> delete(@RequestParam String id){
        return userService.delete(id);
    }


    @PostMapping("update")
    public ServerResponse<String> update(@RequestParam String id, int status){
        return userService.update(id,status);
    }

}
