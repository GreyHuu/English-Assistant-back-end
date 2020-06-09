package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.CompositionBankEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompositionBankDao {

    /**
     * 查询题库中的所有作文题
     * @param
     * @return List<CompositionBankEntity>
     */
    @Select("SELECT * FROM composition_bank")
    public List<CompositionBankEntity> getAllCompositions();

    /**
     *@description 增加作文引用数
     * @param cpt_id,cpt_reference
     * @return 1/0
     */
    @Update("UPDATE composition_bank SET cpt_reference=#{cpt_reference} WHERE cpt_id=#{cpt_id}")
    public int updateReference(@Param("cpt_id") Integer cpt_id, @Param("cpt_reference") Integer cpt_reference);

    /**
     * 通过作文题目的ID查询一篇作文题目
     * @param cpt_id
     * @return CompositionBankEntity
     */
    @Select("SELECT * FROM composition_bank WHERE cpt_id=#{cpt_id}")
    public CompositionBankEntity getACompositionByID(Integer cpt_id);












    /**
     * @description 通过关键词搜索作文题
     * @param keyword
     * @return List<CompositionBankEntity>
     */
    @Select("SELECT * FROM composition_bank WHERE keyword=#{keyword}")
    public List<CompositionBankEntity> getCompositionByKeyword(String keyword);

    public int insertSelective(CompositionBankEntity record);

    public CompositionBankEntity selectByPrimaryKey(Integer cpt_id);

    public int updateByPrimaryKeySelective(CompositionBankEntity record);

}