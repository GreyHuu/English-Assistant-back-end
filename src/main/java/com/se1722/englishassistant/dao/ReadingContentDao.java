package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingContentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ReadingContentDao {

    @Select("SELECT * FROM reading_content WHERE group_id = #{id}")
    List<ReadingContentEntity> selectAllByGroupId(Integer id);

    @Select("SELECT group_id FROM reading_content WHERE id = #{id}")
    Integer selectGroupIdByReadingID(Integer id);

    int deleteByPrimaryKey(Object o);


    int insert(ReadingContentEntity readingContentEntity);


    List<ReadingContentEntity> selectAll();


    ReadingContentEntity selectByPrimaryKey(Object o);


    int updateByPrimaryKey(ReadingContentEntity readingContentEntity);
}