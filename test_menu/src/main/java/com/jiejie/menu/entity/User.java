package com.jiejie.menu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * @version 1.0
 * @description: TODO
 * @author: 22783
 * @date: 2023/10/28
 **/
@Data
@RequiredArgsConstructor
public class User {

    @TableId(value = "userId",type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String roleCode;

    @TableField(fill = FieldFill.INSERT)        // 新增的时候填充数据
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) // 新增或修改的时候填充数据
    private Date updateTime;
}
