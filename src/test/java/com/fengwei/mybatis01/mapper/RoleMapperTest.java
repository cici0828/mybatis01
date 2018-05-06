package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.SysRole;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class RoleMapperTest extends BaseMapperTest {
    @Test
    public void tetSelectRoleById(){
        SqlSession sqlSession = getSqlSession();
        try
        {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = roleMapper.selectRoleById(1L);
            System.out.println(sysRole.getRoleName());

        }
        finally {
            sqlSession.close();

        }
    }

    @Test
    public void tetSelectRoleById2(){
        SqlSession sqlSession = getSqlSession();
        try
        {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = roleMapper.selectRoleById2(1L);
            System.out.println(sysRole.getId());

        }
        finally {
            sqlSession.close();

        }
    }
}
