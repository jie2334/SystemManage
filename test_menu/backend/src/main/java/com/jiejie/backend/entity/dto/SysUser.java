package com.jiejie.backend.entity.dto;

import com.jiejie.backend.entity.BaseData;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2023-11-08 16:30:39
 */
@Data
public class SysUser implements BaseData,Serializable {
    private static final long serialVersionUID = 797383903167773072L;
    /**
     * 会员id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机
     */
    private String phone;
    /**
     * email地址
     */
    private String email;
    /**
     * 头像地址
     */
    private String headUrl;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 岗位id
     */
    private Long postId;
    /**
     * 微信openId
     */
    private String openId;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态（1：正常 0：停用）
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer isDeleted;


}

