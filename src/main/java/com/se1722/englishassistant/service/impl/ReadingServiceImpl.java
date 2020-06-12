package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.ReadingGroupDao;
import com.se1722.englishassistant.entity.ReadingGroupEntity;
import com.se1722.englishassistant.service.ReadingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {
    @Resource
    private ReadingGroupDao readingGroupDao;


    @Override
    public List<ReadingGroupEntity> getAllReadingGroup() {
        return readingGroupDao.getAllReadingGroup();
    }

    /**
     * 获得题目组的满分
     *
     * @param id
     * @return
     */
    @Override
    public Double selectFullMarkById(Integer id) {
        return readingGroupDao.selectFullMarkById(id);
    }

    /**
     * 通过title模糊搜索
     *
     * @param title
     * @return
     */
    @Override
    public List<ReadingGroupEntity> searchReadingGroupEntity(String title) {
        return readingGroupDao.searchReadingGroupEntity(title);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return readingGroupDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(ReadingGroupEntity record) {
        return readingGroupDao.insertSelective(record);
    }

    @Override
    public ReadingGroupEntity selectByPrimaryKey(Integer id) {
        return readingGroupDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ReadingGroupEntity record) {
        return readingGroupDao.updateByPrimaryKeySelective(record);
    }


}
