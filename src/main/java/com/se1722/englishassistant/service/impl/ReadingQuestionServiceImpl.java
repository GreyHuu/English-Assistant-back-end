package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.ReadingQuestionDao;
import com.se1722.englishassistant.entity.ReadingQuestionEntity;
import com.se1722.englishassistant.service.ReadingQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadingQuestionServiceImpl implements ReadingQuestionService {
    @Resource
    private ReadingQuestionDao readingQuestionDao;

    @Override
    public int deleteByPrimaryKey(Object o) {
        return readingQuestionDao.deleteByPrimaryKey(o);
    }

    @Override
    public List<ReadingQuestionEntity> selectAll() {
        return readingQuestionDao.selectAll();
    }

    @Override
    public ReadingQuestionEntity selectByPrimaryKey(Object o) {
        return readingQuestionDao.selectByPrimaryKey(o);
    }

    @Override
    public int updateByPrimaryKey(ReadingQuestionEntity readingQuestionEntity) {
        return readingQuestionDao.updateByPrimaryKey(readingQuestionEntity);
    }

    @Override
    public int insertUseGeneratedKeys(ReadingQuestionEntity readingQuestionEntity) {
        return readingQuestionDao.insertUseGeneratedKeys(readingQuestionEntity);
    }
}
