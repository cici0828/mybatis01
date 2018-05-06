package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.SysRole;
import com.fengwei.mybatis01.model.SysUser;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserMapperTest extends BaseMapperTest {
    enum Color {
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private Color(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (Color c : Color.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    @Test
    public void testSelectById() {
        Color color = Color.GREEN;
        System.out.println(color.getIndex());
//        SqlSession sqlSession = super.getSqlSession();
//        try {
//            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            SysUser user = userMapper.selectById(11L);
//            Assert.assertNotNull(user);
//            Assert.assertEquals("fengwei", user.getUserName());
//        } finally {
//            sqlSession.close();
//        }

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
            List<SysUser> userList = userMapper.selectAll();
            Assert.assertNotNull(userList);
            Assert.assertTrue(userList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserId(11L);
            Assert.assertNotNull(roleList);
            for (SysRole sysRole : roleList) {
                System.out.println(sysRole.getRoleName() + "\n" + sysRole.getCreateTime() + "\n" +
                        sysRole.getUser().getUserName());
            }
        } finally {
            sqlSession.close();

        }
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("scj");
            user.setUserPassword("123456");
            user.setUserEmail("scj@hotmailc.com");
            user.setUserInfo("scj info1111");
            user.setHeadImg(new byte[]{1, 2, 33, 5});
            user.setCreateTime(new Date());
            int result = userMapper.insertUser2(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
            System.out.println(user.getId());
        } finally {
            sqlSession.rollback();
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(11L);
            Assert.assertEquals("fengwei", user.getUserName());
            user.setUserName("fengwei1");
            user.setUserEmail("dde20011@hotmail.com");
            int result = userMapper.updateById(user);
            Assert.assertEquals("插入成功", 1, result);
            user = userMapper.selectById(11L);
            Assert.assertEquals("fengwei1", user.getUserName());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(11L, 1);
            Assert.assertNotNull(roleList);
            Assert.assertTrue("kkkk", roleList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectUserByIdOrUserName() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setId(11L);
            query.setUserName("fengwei1");
            SysUser sysUser = userMapper.selectUserByIdOrUserName(query);
            System.out.println("1:" + sysUser.getUserName());
            query.setId(null);
            query.setUserName("fengwei");
            sysUser = userMapper.selectUserByIdOrUserName(query);
            System.out.println("2:" + sysUser.getUserName());
            query.setUserName(null);
            query.setId(null);
            sysUser = userMapper.selectUserByIdOrUserName(query);
            Assert.assertNull(sysUser);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAll2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setId(11L);
            query.setUserName("fengwei");
            List<SysUser> sysUserList = userMapper.selectUserAll2(query);
            for (SysUser sysUser : sysUserList) {
                System.out.println(sysUser.getUserName());
            }
            query.setId(null);
            query.setUserName(null);
            sysUserList = userMapper.selectUserAll2(query);
            System.out.println(sysUserList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserByIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<Long>();
            idList.add(11L);
            idList.add(13L);
            List<SysUser> sysUserList = userMapper.selectUserByIdList(idList);
            System.out.println(sysUserList.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserByIdArray () {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Long[] idList = new Long[5];
            idList[0] = 11L;
            idList[1] = 13L;
            List<SysUser> sysUserList = userMapper.selectUserByIdArray(idList);
            System.out.println(sysUserList.size());
        } finally {
            sqlSession.close();
        }
    }


}
