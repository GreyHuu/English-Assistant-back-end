package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingQuestionGroupEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadingQuestionGroupDao {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(ReadingQuestionGroupEntity record);

    public ReadingQuestionGroupEntity selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(ReadingQuestionGroupEntity record);

}