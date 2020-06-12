package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.entity.NewsEntity;
import com.se1722.englishassistant.service.NewsService;
import com.se1722.englishassistant.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl  implements NewsService {
    /**
     *  mybatis-spring-boot-starter自动扫描有mapper 注解的接口实现装配
     *  所以忽视以下错误
     */
    @Autowired
    private NewsDao newsdao;
    @Override
    public List<NewsEntity> findAllNews() {

        return newsdao.findAllNews();
    }
}
