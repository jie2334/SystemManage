package com.jiejie.backend.filter;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/11/8
 **/

import com.auth0.jwt.interfaces.DecodedJWT;
import com.jiejie.backend.utils.Constant;
import com.jiejie.backend.utils.JwtUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 用于对请求头中Jwt令牌进行校验的工具，为当前请求添加用户验证信息
 * 并将用户的ID存放在请求对象属性中，方便后续使用
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    JwtUtils utils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        DecodedJWT jwt = utils.resolveJwt(authorization);

        if (jwt != null) {

            UserDetails user = utils.toUser(jwt);

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            //将验证信息丢到SecurityContext里面
            SecurityContextHolder.getContext().setAuthentication(token);
            //将用户id丢入session里面
            request.setAttribute("id", utils.toId(jwt));
        }
        filterChain.doFilter(request, response);
    }
}
