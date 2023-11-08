package com.jiejie.menu.result;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/10/23
 **/
@AllArgsConstructor
@NoArgsConstructor
public enum ResultCode implements IResultCode {
    SUCCESS("200","一切ok"),
    ERROR("300","系统错误"),
    NOT_FOUND("404","找不到资源"),
    METHOD_NOT_ALLOW("405","客户端请求中的方法被禁止"),
    SERVER_ERROR("500","服务器内部错误，无法完成请求");

    private String code;

    private String msg;

    @Override
    public String toString() {
        return "ResultCode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
