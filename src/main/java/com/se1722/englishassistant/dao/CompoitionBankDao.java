package com.se1722.englishassistant.dao;


import com.se1722.englishassistant.entity.CompositionBankEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompoitionBankDao {

    public int deleteByPrimaryKey(Integer cpt_id);

    public int insertSelective(CompositionBankEntity record);

    public CompositionBankEntity selectByPrimaryKey(Integer cpt_id);

    public int updateByPrimaryKeySelective(CompositionBankEntity record);

}