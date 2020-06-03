package com.se1722.englishassistant.service;


import com.se1722.englishassistant.entity.ReadingQuestionEntity;

import java.util.List;

public interface ReadingQuestionService {
    int deleteByPrimaryKey(Object o);

    List<ReadingQuestionEntity> selectAll();

    ReadingQuestionEntity selectByPrimaryKey(Object o);

    int updateByPrimaryKey(ReadingQuestionEntity readingQuestionEntity);

    int insertUseGeneratedKeys(ReadingQuestionEntity readingQuestionEntity);
}
