package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.SysRole;
import com.fengwei.mybatis01.model.SysUser;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;


public class UserMapperTest extends BaseMapperTest {
    @Test
    public void testSelectById() {
        SqlSession sqlSession = super.getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(11L);
            Assert.assertNotNull(user);
            Assert.assertEquals("fengwei", user.getUserName());
        } finally {
            sqlSession.close();
        }
//        sqlSession.select("selectById", 11L, new ResultHandler() {
//            public void handleResult(ResultContext resultContext) {
//                    System.out.println("--------------------------------");
//                    SysUser sysUser = (SysUser)resultContext.getResultObject();
//                    System.out.println(sysUser.getUserName());
//            }
//        });
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> userList =  userMapper.selectAll();
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserId(11L);
            Assert.assertNotNull(roleList);
            for(SysRole sysRole: roleList){
                System.out.println(sysRole.getRoleName() + "\n" + sysRole.getCreateTime() + "\n" +
                sysRole.getUser().getUserName());
            }
        }
        finally {
            sqlSession.close();

        }
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = getSqlSession();
        try{
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("scj");
            user.setUserPassword("123456");
            user.setUserEmail("scj@hotmailc.com");
            user.setUserInfo("scj info1111");
            user.setHeadImg(new byte[]{1,2,33,5});
            user.setCreateTime(new Date());
            int result = userMapper.insertUser(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
        }
        finally {
            sqlSession.rollback();
            //sqlSession.commit();
            sqlSession.close();

        }
    }


}
