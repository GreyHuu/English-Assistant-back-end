package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingGroupEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ReadingGroupDao {
    /**
     * 获得全部的题目组
     *
     * @return
     */
    @Select("SELECT * FROM reading_group")
    List<ReadingGroupEntity> getAllReadingGroup();

    /**
     * 模糊查找title
     *
     * @param title
     * @return
     */
    @Select("SELECT * FROM reading_group WHERE title like concat('%',#{title},'%') ")
    List<ReadingGroupEntity> searchReadingGroupEntity(String title);

    /**
     * 通过总分获得满分
     *
     * @param id
     * @return
     */
    @Select("SELECT full_mark FROM reading_group WHERE id = #{id} ")
    Double selectFullMarkById(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ReadingGroupEntity record);

    ReadingGroupEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReadingGroupEntity record);
}