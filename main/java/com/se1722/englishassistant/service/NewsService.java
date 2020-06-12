package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.NewsEntity;

import java.util.List;

public interface NewsService {
    List<NewsEntity> findAllNews();
}
