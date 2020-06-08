package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.ReadingGroupEntity;

import java.util.List;

public interface ReadingService {
    List<ReadingGroupEntity> getAllReadingGroup();
    Double selectFullMarkById(Integer id);
    int deleteByPrimaryKey(Integer id);
    List<ReadingGroupEntity> searchReadingGroupEntity(String title);


    int insertSelective(ReadingGroupEntity record);

    ReadingGroupEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReadingGroupEntity record);

}
