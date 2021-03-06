package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.ReadingGroupEntity;
import com.se1722.englishassistant.entity.ReadingListEntity;

import java.util.Date;
import java.util.List;

public interface ReadingListService {
    List<ReadingListEntity> getAllListByUserId(Integer id);
    int selectCountByGroupID(Integer id);
    Double selectMaxScoreByGroupID(Integer id);
    Date selectLastTimeByGroupID(Integer id);
    Integer insertReadingList(ReadingListEntity readingListEntity);
    ReadingListEntity getById(Integer id);
    int selectCountByUserID(Integer id);
    List<ReadingListEntity> selectReadingListByGroupID(Integer id);
    Integer deleteReadingList(Integer id);
}
