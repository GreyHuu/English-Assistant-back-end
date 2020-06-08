package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.WordUserPlansEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordUserPlansDao {

    public int insertSelective(WordUserPlansEntity record);

}