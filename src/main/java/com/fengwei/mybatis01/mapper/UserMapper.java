package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.SysUser;

import java.util.List;

public interface UserMapper {
    public List<SysUser> selectAll();
    public SysUser selectById(Long id);
}
