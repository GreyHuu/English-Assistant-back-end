package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsDao {
    /**
     * 取出数据库中的新闻信息
     * @return
     */
    @Select("SELECT * FROM news")
    List<NewsEntity> findAllNews();

}