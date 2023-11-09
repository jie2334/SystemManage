package com.jiejie.backend.service.impl;

import com.jiejie.backend.entity.dto.SysUser;
import com.jiejie.backend.entity.vo.RegisterVo;
import com.jiejie.backend.service.SmSService;
import com.jiejie.backend.service.UserService;
import com.jiejie.backend.utils.Constant;
import com.jiejie.backend.utils.HttpUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/11/9
 **/
@Service
public class SmSServiceImpl implements SmSService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;


    @Override
    public RegisterVo sendCode(String phone) {
        //生成验证码
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000; // 生成一个1000到9999之间的随机数
        String code = String.format("%04d", randomNumber); // 将随机数格式化为四位数的字符串
        //设置验证码有效期
        stringRedisTemplate.opsForValue().set(Constant.REGISTER_PHONE_NUMBER+phone,code, 5,TimeUnit.MINUTES);

        return sendMessage(phone,code);
    }

    @Override
    public Boolean register(RegisterVo registerVo) {

        /**
         * 从redis里面去除验证码，判断是否过期，过期重新发送验证码
         * 没有过期，往数据库里面储存用户，并返回用户的token
         *
         * 可以通过手机号和邮箱注册
         *
         */
        boolean save = false;
        String code = stringRedisTemplate.opsForValue().get(registerVo.getPhone());
        if (!StringUtils.isBlank(code)){
            SysUser user = new SysUser();
            //bean之间的转换
            BeanUtils.copyProperties(registerVo, user);
            user.setPassword(new BCryptPasswordEncoder().encode(registerVo.getPassword()));
            save = userService.save(user);
        }
        return save;
    }

    private RegisterVo sendMessage(String phone, String code) {
        String host = "https://gyytz.market.alicloudapi.com";
        String path = "/sms/smsSend";
        String method = "POST";
        String appcode = "b1fe0d1273f84568ad57d173aafa58bf";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<>();
        querys.put("mobile", phone);
        querys.put("param",  "**code**:"+code+",**minute**:5");

//smsSignId（短信前缀）和templateId（短信模板），可登录国阳云控制台自助申请。参考文档：http://help.guoyangyun.com/Problem/Qm.html

        querys.put("smsSignId", "2e65b1bb3d054466b82f0c9d125465e2");
        querys.put("templateId", "908e94ccf08b4476ba6c876d13f084ad");
        Map<String, String> bodys = new HashMap<>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从\r\n\t    \t* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java\r\n\t    \t* 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RegisterVo(phone,code);
    }
}
