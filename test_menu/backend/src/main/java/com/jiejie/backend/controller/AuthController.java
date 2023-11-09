package com.jiejie.backend.controller;

import com.jiejie.backend.entity.RestBean;
import com.jiejie.backend.entity.vo.RegisterVo;
import com.jiejie.backend.service.SmSService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
     * 发送短信
     * @param phone
     * @return
     */
    @GetMapping(value = "/sms/{phone}")
    public RestBean<RegisterVo> sendCode( @PathVariable @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确") String phone) {
        RegisterVo registerVo = service.sendCode(phone);
        return RestBean.success(registerVo);
    }

    /**
     * 注册接口
     * 进行验证
     */
    @PostMapping("/register")
    public RestBean<Boolean> register(@Valid @RequestBody RegisterVo registerVo){
        Boolean register = service.register(registerVo);
        return RestBean.success(register);
    }
}
