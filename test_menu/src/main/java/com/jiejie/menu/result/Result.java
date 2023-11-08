package com.jiejie.menu.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/10/23
 **/
@Data
public class Result <T> implements Serializable {
    private String code;

    private T data;

    private String msg;

    //返回null
    public static <T> Result<T> success(){
        return success(null);
    }
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    private static <T> Result<T> result(String code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }
    public static <T> Result<T> failed() {
        return result(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg(), null);
    }
    public static <T> Result<T> failed(String msg) {
        return result(ResultCode.ERROR.getCode(), msg, null);
    }

    public static boolean isSuccess(Result<?> result) {
        return result != null && ResultCode.SUCCESS.getCode().equals(result.getCode());
    }
}

