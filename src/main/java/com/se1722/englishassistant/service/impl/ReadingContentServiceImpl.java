package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.ReadingContentDao;
import com.se1722.englishassistant.entity.ReadingContentEntity;
import com.se1722.englishassistant.service.ReadingContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReadingContentServiceImpl implements ReadingContentService {
    @Resource
    private ReadingContentDao readingContentDao;

    /**
     * 获得全部的题目
     * @return
     */
    @Override
    public List<ReadingContentEntity> selectAll() {
        return readingContentDao.selectAll();
    }

    /**
     * 根据阅读题组id获得全部的文章
     * @param id
     * @return
     */
    @Override
    public List<ReadingContentEntity> selectAllByGroupId(Integer id) {
        return readingContentDao.selectAllByGroupId(id);
    }

    /**
     * 通过文章id获得组别id
     * @param id
     * @return
     */
    @Override
    public Integer selectGroupIdByReadingID(Integer id) {
        return readingContentDao.selectGroupIdByReadingID(id);
    }
}
