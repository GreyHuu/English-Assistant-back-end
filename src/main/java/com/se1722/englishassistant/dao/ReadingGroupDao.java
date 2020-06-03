package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingGroupEntity;
import com.se1722.englishassistant.service.PublicDBInterface;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ReadingGroupDao extends PublicDBInterface<ReadingGroupEntity> {
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

}