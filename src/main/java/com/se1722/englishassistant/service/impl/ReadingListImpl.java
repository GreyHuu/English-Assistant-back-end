package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.ReadingListDao;
import com.se1722.englishassistant.entity.ReadingListEntity;
import com.se1722.englishassistant.service.ReadingListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ReadingListImpl implements ReadingListService {
    @Resource
    private ReadingListDao readingListDao;

    @Override
    public int selectCountByGroupID(Integer id) {
        return readingListDao.selectCountByGroupID(id);
    }

    @Override
    public Double selectMaxScoreByGroupID(Integer id) {
        return readingListDao.selectMaxScoreByGroupID(id);
    }

    @Override
    public Date selectLastTimeByGroupID(Integer id) {
        return readingListDao.selectLastTimeByGroupID(id);
    }

    @Override
    public Integer insertReadingList(ReadingListEntity readingListEntity) {
        return readingListDao.insertReadingList(readingListEntity);
    }

    @Override
    public ReadingListEntity getById(Integer id) {
        return readingListDao.getById(id);
    }
}
