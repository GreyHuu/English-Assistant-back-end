package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.ReadingQuestionGroupDao;
import com.se1722.englishassistant.service.ReadingQuestionGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReadingQuestionGroupServiceImpl implements ReadingQuestionGroupService {
    @Resource
    private ReadingQuestionGroupDao readingQuestionGroupDao;

    /**
     * 通过文章id获得问题id
     *
     * @param id
     * @return
     */
    @Override
    public String getQuestionsIdByReadingId(Integer id) {
        return readingQuestionGroupDao.getQuestionsIdByReadingId(id);
    }
}
