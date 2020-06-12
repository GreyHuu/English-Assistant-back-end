package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.WordNewEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordNewDao {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(WordNewEntity record);

    public WordNewEntity selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(WordNewEntity record);

}