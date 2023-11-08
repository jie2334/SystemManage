package com.jiejie.backend;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jiejie.backend.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

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
}
