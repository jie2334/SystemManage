package com.jiejie.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiejie.backend.entity.dto.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
