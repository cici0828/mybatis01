package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.SysRole;
import com.fengwei.mybatis01.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<SysUser> selectAll();

    public SysUser selectById(Long id);

    public List<SysRole> selectRolesByUserId(Long id);

    public int insertUser(SysUser sysUser);

    public int insertUser2(SysUser sysUser);

    public int updateById(SysUser sysUser);

    public List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId1") Long userId,
                                                           @Param("enabled2") Integer enabled);

    public List<SysUser> selectUserByIdOrUserName(SysUser sysUser);

    public List<SysUser> selectUserAll2(SysUser sysUser);

    public List<SysUser> selectUserByIdList(List<Long> idList);

    public List<SysUser> selectUserByIdArray(Long[] idList);

    public List<SysUser> selectUserAndRoleById2(Long id);

    public List<SysUser> selectUserAndRoleById3(Long id);

}
