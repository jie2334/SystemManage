package com.jiejie.menu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiejie.menu.entity.User;
import com.jiejie.menu.mapper.UserMapper;
import com.jiejie.menu.result.Result;
import com.jiejie.menu.service.impl.UserServiceImpl;
import com.jiejie.menu.utils.PageQuery;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/10/28
 **/
@Slf4j
@RestController
@RequestMapping("/sysRole")
public class RoleController {

    @Resource
    private UserServiceImpl userService;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/{pageStart}/{size}")
    public Result<Page<User>> getList(@RequestParam(value = "page", defaultValue = "1") int pageStart,
                                      @RequestParam(value = "size", defaultValue = "10") int size){

        log.info("进入接口~~~~~~");

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        Page<User> userPage = new Page<>(pageStart, size);

        Page<User> page = userMapper.selectPage(userPage, wrapper);

        return Result.success(page);
    }

}
