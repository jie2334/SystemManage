package com.jiejie.menu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiejie.menu.entity.User;
import com.jiejie.menu.mapper.UserMapper;
import com.jiejie.menu.result.Result;
import com.jiejie.menu.service.UserService;
import com.jiejie.menu.service.impl.UserServiceImpl;
import com.jiejie.menu.utils.PageQuery;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/10/28
 **/

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;
    @Resource
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result login(@RequestBody User user){

        String username = user.getUsername();
        String password = user.getPassword();
        if (userService.isUserExist(username, password)) {
            Map<String, Object> userToken = new HashMap<>(4);
            userToken.put("token","admin-token");
            return Result.success(userToken);
        } else {
            return Result.failed("用户名或密码错误");
        }

    }


    @GetMapping("/info")
    public Result useInfo(HttpServletRequest request) throws Exception {
        log.info("进入接口");
        String token = request.getParameter("token");
        Map<String, Object> result = new HashMap<>(8);
        ArrayList roles = new ArrayList<>();
        String allowableToken = "admin-token";
        if (token.equals(allowableToken)) {
            roles.add("admin");
            result.put("roles", roles);
            result.put("introduction", "我是超级管理员");
            result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        }
        return Result.success(result);
    }


    @PostMapping("/logout")
    public Result logout() throws Exception {
        return Result.success();
    }



}
