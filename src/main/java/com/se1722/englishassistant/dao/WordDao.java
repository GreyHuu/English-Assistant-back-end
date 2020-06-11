package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.WordEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WordDao {

    @Select("select * from word where collect=#{type}")
    public List<WordEntity> findAll(int type);

    @Insert("insert into word_new(word_id, user_id) values (#{word_id},#{user_id})")
    public Integer save(@Param(value="word_id") Integer word_id, @Param(value="user_id")Integer user_id);

    @Delete("delete from word_new where word_id =#{word_id} and user_id=#{user_id}")
    Integer delete(@Param(value="word_id")Integer word_id, @Param(value="user_id")Integer user_id);
    public int deleteByPrimaryKey(Integer id);



//    通过单词查意思
    @Select("select * from word where englishWord like CONCAT('%', #{word}, '%') or chineseWord like CONCAT('%', #{word}, '%')")
    List<WordEntity> getMeanByWord(@Param(value = "word") String word);
}