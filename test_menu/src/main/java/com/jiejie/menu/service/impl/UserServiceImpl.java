package com.jiejie.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiejie.menu.entity.User;
import com.jiejie.menu.mapper.UserMapper;
import com.jiejie.menu.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/10/28
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;



    /**
     * 判断用户是否存在，存在则返回true，不存在则返回false
     * @param username 用户名
     * @param password 用户密码
     * @return true,false
     */
    public boolean isUserExist(String username, String password) {
        //创建对象，泛型里加上实体对象
        QueryWrapper<User> wrapperUser = new QueryWrapper<>();
        // 先创建一个 hashmap，然后把多个条件put进去，再调用allEq
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        // 设置查询的条件
        wrapperUser.allEq(map);
        // 调用方法查询一个返回记录
        Long count = userMapper.selectCount(wrapperUser);
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

}
