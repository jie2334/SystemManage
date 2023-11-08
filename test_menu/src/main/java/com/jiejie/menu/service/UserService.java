package com.jiejie.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiejie.menu.entity.User;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/10/28
 **/
public interface UserService extends IService<User> {

    SysUser findAccountByNameOrEmail(String text);
}
