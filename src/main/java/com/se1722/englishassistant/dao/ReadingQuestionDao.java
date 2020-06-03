package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingQuestionEntity;
import com.se1722.englishassistant.service.PublicDBInterface;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ReadingQuestionDao extends PublicDBInterface<ReadingQuestionEntity> {
    @Override
    int deleteByPrimaryKey(Object o);

    @Override
    List<ReadingQuestionEntity> selectAll();

    @Override
    ReadingQuestionEntity selectByPrimaryKey(Object o);

    @Override
    int updateByPrimaryKey(ReadingQuestionEntity readingQuestionEntity);

    @Override
    int insertUseGeneratedKeys(ReadingQuestionEntity readingQuestionEntity);
}