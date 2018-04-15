package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryMapperTest extends BaseMapperTest {


    @Test
    public void testSelectAll(){
        SqlSession sqlSession = super.getSqlSession();
        List<Country> countryList = sqlSession.selectList("com.fengwei.mybatis01.mapper.CountryMapper.selectAll");
        printCountryList(countryList);
        sqlSession.close();
    }

    private void printCountryList(List<Country> countryList){
        for(Country country: countryList){
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }


}
