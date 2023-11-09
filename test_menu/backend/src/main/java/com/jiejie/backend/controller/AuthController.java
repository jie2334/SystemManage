package com.jiejie.backend.controller;

import com.jiejie.backend.entity.RestBean;
import com.jiejie.backend.entity.vo.RegisterVo;
import com.jiejie.backend.service.SmSService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/11/8
 **/
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    SmSService service;

    /**
     * 注册接口
     */
    @GetMapping(value = "/sms/{phone}")
    public RestBean<RegisterVo> register(@PathVariable String phone) {
        RegisterVo registerVo = service.sendCode(phone);
        return RestBean.success(registerVo);
    }
}
