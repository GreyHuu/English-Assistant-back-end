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

    /**
     * 通过问题找到所属的题目组   进而获得总分
     *id左右两边都有逗号
     * @param id
     * @return
     */
    @Override
    public Integer getGroupIdByQuestionId(Integer id) {
        return readingQuestionGroupDao.getGroupIdByQuestionId(id);
    }

    /**
     * id前面没逗号
     * @param id
     * @return
     */
    @Override
    public Integer getGroupIdByQuestionId2(Integer id) {
        return readingQuestionGroupDao.getGroupIdByQuestionId2(id);
    }

    /**
     * id后面没逗号
     * @param id
     * @return
     */
    @Override
    public Integer getGroupIdByQuestionId3(Integer id) {
        return readingQuestionGroupDao.getGroupIdByQuestionId3(id);
    }
}
