package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingGroupEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadingGroupDao {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(ReadingGroupEntity record);

    public ReadingGroupEntity selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(ReadingGroupEntity record);

}