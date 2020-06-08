package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.NewsCommentsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsCommentsDao {

    public int deleteByPrimaryKey(String comment);

    public int insertSelective(NewsCommentsEntity record);

    public NewsCommentsEntity selectByPrimaryKey(String comment);

    public int updateByPrimaryKeySelective(NewsCommentsEntity record);

}