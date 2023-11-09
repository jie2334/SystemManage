package com.jiejie.backend.controller;

import com.jiejie.backend.entity.RestBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/11/8
 **/
@RestController
@RequestMapping("/error")
public class TestController {


    @GetMapping("/hello")
    public String test(){
        return "jiejie";
    }
}
