package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.NewsCommentsDao;
import com.se1722.englishassistant.entity.NewsCommentsEntity;
import com.se1722.englishassistant.service.NewsCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NewsCommentsServiceImpl implements NewsCommentsService {
    @Autowired
    private NewsCommentsDao newsCommentsDao;


    @Override
    public List<NewsCommentsEntity> findAllComments() {
        return newsCommentsDao.getAllComments();
    }

    @Override
    public List<NewsCommentsEntity> findTheComments(int id) {
        return newsCommentsDao.selectByUserid(id);
    }

    @Override
    public int addComments(NewsCommentsEntity record) {
        return newsCommentsDao.insertComment(record);
    }

    @Override
    public int deleteComments(Integer c_id) {
        return newsCommentsDao.deleteByPrimaryKey(c_id);
    }
}
