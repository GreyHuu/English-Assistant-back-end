package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingQuestionEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadingQuestionDao {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(ReadingQuestionEntity record);

    public ReadingQuestionEntity selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(ReadingQuestionEntity record);

}