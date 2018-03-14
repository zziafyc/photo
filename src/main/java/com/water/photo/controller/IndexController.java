package com.water.photo.controller;

import com.google.common.collect.Lists;
import com.water.photo.service.UserService;
import com.water.photo.vo.NavVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author https://github.com/zziaguan/
 */
@Slf4j
@Controller
public class IndexController {

    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "首页", hidden = true)
    @GetMapping("")
    public String index(Model model) {
        log.debug("进入首页");
        model.addAttribute("nav", nav());
        return "index";
    }


    @ApiOperation(value = "导航数据", hidden = true)
    @ResponseBody
    @GetMapping("nav")
    public List<NavVo> nav() {
        List<NavVo> list = Lists.newArrayList();
        list.add(new NavVo("首页", "desktop"));
        list.add(new NavVo("用户列表", "user"));
        list.add(new NavVo("数据导出", "export"));
        return list;
    }

    @ApiOperation(value = "登陆页面", hidden = true)
    @GetMapping("login")
    public String login() {
        return "login";
    }

    @ApiOperation(value = "用户数据", hidden = true)
    @GetMapping("user")
    public String user() {
        return "user/user";
    }

    @ApiOperation(value = "更新用户", hidden = true)
    @GetMapping("userUpdate")
    public String userUpdate(Model model,String id) {
        model.addAttribute("user",userService.getUser(id));
        return "user/userUpdate";
    }

    @ApiOperation(value = "删除用户", hidden = true)
    @GetMapping("userDelete")
    public String userDelete(Model model,String id) {
        model.addAttribute("user",userService.getUser(id));
        return "user/userDelete";
    }

    @ApiOperation(value = "用户数据", hidden = true)
    @GetMapping("export")
    public String export() {
        return "data/export";
    }


    @ApiOperation(value = "桌面", hidden = true)
    @GetMapping("desktop")
    public String desktop() {
        return "desktop";
    }
}
