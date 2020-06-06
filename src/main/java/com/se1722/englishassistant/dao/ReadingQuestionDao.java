package com.se1722.englishassistant.dao;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;
import com.se1722.englishassistant.entity.ReadingQuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component
public interface ReadingQuestionDao {

    @Select("SELECT * FROM reading_question WHERE id = #{id}")
    ReadingQuestionEntity selectByPrimaryKey(Integer id);

    @Select("SELECT answer FROM reading_question WHERE id = #{id}")
    String selectAnswerById(Integer id);

    int deleteByPrimaryKey(Object o);

    List<ReadingQuestionEntity> selectAll();

    int updateByPrimaryKey(ReadingQuestionEntity readingQuestionEntity);

    int insertUseGeneratedKeys(ReadingQuestionEntity readingQuestionEntity);
}