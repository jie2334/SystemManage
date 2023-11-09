package com.jiejie.backend;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.jiejie.backend.entity.dto.SysUser;
import com.jiejie.backend.service.UserService;
import com.jiejie.backend.utils.Constant;
import com.jiejie.backend.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class BackendApplicationTests {

    @Resource
    UserService userService;
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    JwtUtils jwtUtils;


    @Test
    void contextLoads() {


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println("encode = " + encode);
    }


    @Test
    public void testToken(){
        DecodedJWT jwt = jwtUtils.resolveJwt("Bearer eyJhbGciOiJIUzI1NiJ9.e30.iIoTq1YPWmmJpam_TRVGV2aiHttk5MzW5pNBLsXZvgo");
        if (jwt == null) {
            System.out.println("jwt是空的！！！");
        }
    }

    @Test
    public void testOSS() {
        //生成验证码
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; // 生成一个1000到9999之间的随机数
        String uuid = String.format("%04d", randomNumber); // 将随机数格式化为四位数的字符串
        stringRedisTemplate.opsForValue().set(Constant.VERIFY_EMAIL_LIMIT,uuid, 60, TimeUnit.SECONDS);
    }

    @Test
    public void testGetCode() {
        String code = stringRedisTemplate.opsForValue().get("15972613826");
        System.out.println("code = " + code);
    }

    @Test
    public void testSave() {

    }
}
