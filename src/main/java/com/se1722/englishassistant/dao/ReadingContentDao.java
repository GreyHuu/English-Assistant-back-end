package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingContentEntity;
import com.se1722.englishassistant.service.PublicDBInterface;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ReadingContentDao extends PublicDBInterface<ReadingContentEntity> {

    @Select("SELECT * FROM reading_content WHERE group_id = #{id}")
    List<ReadingContentEntity> selectAllByGroupId(Integer id);

    @Override
    int deleteByPrimaryKey(Object o);

    @Override
    int insert(ReadingContentEntity readingContentEntity);

    @Override
    List<ReadingContentEntity> selectAll();

    @Override
    ReadingContentEntity selectByPrimaryKey(Object o);

    @Override
    int updateByPrimaryKey(ReadingContentEntity readingContentEntity);
}