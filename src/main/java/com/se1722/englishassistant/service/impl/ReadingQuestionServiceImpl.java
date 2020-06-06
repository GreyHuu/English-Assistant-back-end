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

    /**
     * 传入id和答案进行比较是否正确
     *
     * @param answer
     * @param id
     * @return
     */
    public Boolean compareQuestion(String answer, Integer id) {
        return answer.equals(selectAnswerById(id));
    }

    /**
     * 通过id获得答案
     *
     * @param id
     * @return
     */
    @Override
    public String selectAnswerById(Integer id) {
        return readingQuestionDao.selectAnswerById(id);
    }

    /**
     * 通过id获得问题
     *
     * @param o
     * @return
     */
    @Override
    public ReadingQuestionEntity selectByPrimaryKey(Integer o) {
        return readingQuestionDao.selectByPrimaryKey(o);
    }

    @Override
    public int deleteByPrimaryKey(Object o) {
        return readingQuestionDao.deleteByPrimaryKey(o);
    }

    @Override
    public List<ReadingQuestionEntity> selectAll() {
        return readingQuestionDao.selectAll();
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
