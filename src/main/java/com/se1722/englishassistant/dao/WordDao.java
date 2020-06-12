package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.WordEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

// Mapper可以使mybatis-spring-boot-starter自动映射
@Mapper
@Component
public interface WordDao {

    @Select("select * from word where collect=#{type}")
    public List<WordEntity> findAll(int type);

//    插入生词
    @Insert("insert into word_new(word_id, user_id) values (#{word_id},#{user_id})")
    public Integer save(@Param(value="word_id") Integer word_id, @Param(value="user_id")Integer user_id);

//    删除生词
    @Delete("delete from word_new where word_id =#{word_id} and user_id=#{user_id}")
    Integer delete(@Param(value="word_id")Integer word_id, @Param(value="user_id")Integer user_id);
    public int deleteByPrimaryKey(Integer id);


//    根据用户id查询所有生词
    @Select("select word.* from word, word_new where word.id = word_new.word_id and word_new.user_id = #{user_id}")
    List<WordEntity> findAllByUserId(Integer user_id);

//    通过单词查意思
    @Select("select * from word where englishWord like CONCAT('%', #{word}, '%') or chineseWord like CONCAT('%', #{word}, '%')")
    List<WordEntity> getMeanByWord(@Param(value = "word") String word);
}