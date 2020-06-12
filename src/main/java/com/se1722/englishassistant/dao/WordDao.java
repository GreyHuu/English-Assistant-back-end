package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.WordEntity;
import org.apache.ibatis.annotations.Mapper;

public interface WordDao {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(WordEntity record);

    public WordEntity selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(WordEntity record);

//    通过单词查意思
    @Select("select * from word where englishWord like CONCAT('%', #{word}, '%') or chineseWord like CONCAT('%', #{word}, '%')")
    List<WordEntity> getMeanByWord(@Param(value = "word") String word);
}