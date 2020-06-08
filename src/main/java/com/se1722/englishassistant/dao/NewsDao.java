package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.NewsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsDao {

    public int deleteByPrimaryKey(Integer news_id);

    public int insertSelective(NewsEntity record);

    public NewsEntity selectByPrimaryKey(Integer news_id);

    public int updateByPrimaryKeySelective(NewsEntity record);

}