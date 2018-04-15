package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.SysUser;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


public class UserMapperTest extends  BaseMapperTest {
    @Test
    public void testSelectById(){
        SqlSession sqlSession = super.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //SysUser user = userMapper.selectById(11L);
        sqlSession.select("selectById", 11L, new ResultHandler() {
            public void handleResult(ResultContext resultContext) {
                    System.out.println("--------------------------------");
                    SysUser sysUser = (SysUser)resultContext.getResultObject();
                    System.out.println(sysUser.getUserName());
            }
        });
    }

    /**
     * @author
     * @re
     */
    public int test(){
        return 2;
    }

}
