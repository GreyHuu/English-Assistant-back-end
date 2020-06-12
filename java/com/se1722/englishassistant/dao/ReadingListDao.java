package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingListEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadingListDao {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(ReadingListEntity record);

    public ReadingListEntity selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(ReadingListEntity record);

}