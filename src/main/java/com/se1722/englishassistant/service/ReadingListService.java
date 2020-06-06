package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.ReadingGroupEntity;

import java.util.Date;
import java.util.List;

public interface ReadingListService {
    int selectCountByGroupID(Integer id);
    Double selectMaxScoreByGroupID(Integer id);
    Date selectLastTimeByGroupID(Integer id);
}
