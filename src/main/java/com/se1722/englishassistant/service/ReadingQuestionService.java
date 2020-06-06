package com.se1722.englishassistant.service;


import com.se1722.englishassistant.entity.ReadingQuestionEntity;

import java.util.List;

public interface ReadingQuestionService {

    Boolean compareQuestion(String answer, Integer id);
    String selectAnswerById(Integer id);

    int deleteByPrimaryKey(Object o);

    List<ReadingQuestionEntity> selectAll();

    ReadingQuestionEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ReadingQuestionEntity readingQuestionEntity);

    int insertUseGeneratedKeys(ReadingQuestionEntity readingQuestionEntity);
}
