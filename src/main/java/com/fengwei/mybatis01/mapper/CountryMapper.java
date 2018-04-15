package com.fengwei.mybatis01.mapper;

import com.fengwei.mybatis01.model.Country;

import java.util.List;

public interface CountryMapper {
    public List<Country> selectAll();
}
