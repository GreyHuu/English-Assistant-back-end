package com.se1722.englishassistant.dao;
import com.se1722.englishassistant.entity.ReadingContentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadingContentDao {

    public int deleteByPrimaryKey(Integer id);

    public int insertSelective(ReadingContentEntity record);

    public ReadingContentEntity selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(ReadingContentEntity record);

}