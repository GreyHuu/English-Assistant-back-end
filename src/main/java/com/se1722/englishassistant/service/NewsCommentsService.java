package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.NewsCommentsEntity;

import java.util.List;

public interface NewsCommentsService {
    List<NewsCommentsEntity> findAllComments();
    List<NewsCommentsEntity> findTheComments(int id);
    int addComments(NewsCommentsEntity record);
    int deleteComments(Integer c_id);
}
