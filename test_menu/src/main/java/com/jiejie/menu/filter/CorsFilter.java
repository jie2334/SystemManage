package com.jiejie.menu.filter;

import com.jiejie.menu.utils.Constant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description:
 * @author: 22783
 * @date: 2023/8/27
 **/
@Component
@Order(Constant.ORDER_CORS)
public class CorsFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //往response里面丢信息
        this.addCorsHeader(request,response);
        chain.doFilter(request, response);
    }


    void addCorsHeader(HttpServletRequest request, HttpServletResponse response){
        //自定义端口访问
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");
        response.addHeader("Access-Control-Allow-Headers", "Authorization,Content-Type");

    }
}
