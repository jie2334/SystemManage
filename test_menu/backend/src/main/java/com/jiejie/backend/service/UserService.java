package com.jiejie.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiejie.backend.entity.dto.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/11/8
 **/
public interface UserService extends IService<SysUser>, UserDetailsService {

    SysUser findAccountByNameOrEmail(String text);
}
