package com.jiejie.backend.entity.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/11/9
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVo {

    @NotNull
    private String username;

    @Email
    private String email;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Length(min = 6,max = 20)
    private String password;
    @Length(max = 4, min = 4)
    private String code;

    public RegisterVo(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }
}
