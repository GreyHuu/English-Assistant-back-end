package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingQuestionGroupEntity;
import com.se1722.englishassistant.service.PublicDBInterface;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface ReadingQuestionGroupDao extends PublicDBInterface<ReadingQuestionGroupEntity> {
    @Select("SELECT question_ids FROM reading_question_group WHERE reading_id = #{id}")
    String getQuestionsIdByReadingId(Integer id);
}