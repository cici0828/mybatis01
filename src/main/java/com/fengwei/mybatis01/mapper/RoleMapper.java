package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.SysRole;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper {
    @Select("select * from sys_role where id = #{id}")
    public SysRole selectRoleById(Long id);

    @Results({
            @Result(property = "id", column = "id" ,id = true),
            @Result(property = "roleName", column="role_name", id = true)
    })
    @Select("select id from sys_role where id=#{id}")
    public SysRole selectRoleById2(Long id);
}
