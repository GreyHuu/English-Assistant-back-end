package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingGroupEntity;

public interface ReadingGroupDao {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(ReadingGroupEntity record);

    public ReadingGroupEntity selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(ReadingGroupEntity record);

}