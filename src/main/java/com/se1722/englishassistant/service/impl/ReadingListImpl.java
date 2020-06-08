package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.ReadingListDao;
import com.se1722.englishassistant.entity.ReadingListEntity;
import com.se1722.englishassistant.service.ReadingListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ReadingListImpl implements ReadingListService {
    @Resource
    private ReadingListDao readingListDao;

    @Override
    public List<ReadingListEntity> getAllListByUserId(Integer id) {
        return readingListDao.getAllListByUserId(id);
    }

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

    @Override
    public int selectCountByUserID(Integer id) {
        return readingListDao.selectCountByUserID(id);
    }

    @Override
    public List<ReadingListEntity> selectReadingListByGroupID(Integer id) {
        return readingListDao.selectReadingListByGroupID(id);
    }
}
