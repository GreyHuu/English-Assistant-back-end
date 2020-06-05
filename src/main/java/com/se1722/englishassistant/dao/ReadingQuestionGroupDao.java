package com.se1722.englishassistant.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface ReadingQuestionGroupDao {
    @Select("SELECT question_ids FROM reading_question_group WHERE reading_id = #{id}")
    String getQuestionsIdByReadingId(Integer id);

    @Select("SELECT reading_id  FROM reading_question_group WHERE question_ids like concat('%',',',#{id},',','%')")
    Integer getGroupIdByQuestionId(Integer id);

    @Select("SELECT reading_id  FROM reading_question_group WHERE question_ids like concat(#{id},',','%')")
    Integer getGroupIdByQuestionId2(Integer id);

    @Select("SELECT reading_id  FROM reading_question_group WHERE question_ids like concat('%',',',#{id})")
    Integer getGroupIdByQuestionId3(Integer id);
}