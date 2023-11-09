package com.jiejie.backend.service;

import com.jiejie.backend.entity.vo.RegisterVo;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/11/9
 **/
public interface SmSService {
    RegisterVo sendCode(String phone);

    Boolean register(RegisterVo registerVo);
}
