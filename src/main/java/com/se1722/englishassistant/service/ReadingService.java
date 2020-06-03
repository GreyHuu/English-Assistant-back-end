package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.ReadingGroupEntity;

import java.util.List;

public interface ReadingService {
    List<ReadingGroupEntity> getAllReadingGroup();

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ReadingGroupEntity record);

    ReadingGroupEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReadingGroupEntity record);
    List<ReadingGroupEntity> searchReadingGroupEntity(String title);
}
