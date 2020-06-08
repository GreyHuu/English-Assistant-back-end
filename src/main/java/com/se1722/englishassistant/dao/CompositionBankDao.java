package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.CompositionBankEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
     * @param cpt_id
     * @return 1/0
     */
    @Update("UPDATE user SET cpt_reference=#{cpt_reference} WHERE cpt_id=#{cpt_id}")
    public int updateReference(int cpt_id, int cpt_reference);

    /**
     * @description 通过关键词搜索作文题
     * @param keyword
     * @return List<CompositionBankEntity>
     */
    @Select("SELECT * FROM composition_bank WHERE keyword=#{keyword}")
    public List<CompositionBankEntity> getAllCompositionsByKeyword(String keyword);

    /**
     * 通过作文题目的ID查询一篇作文题目
     * @param cpt_id
     * @return CompositionBankEntity
     */
    @Select("SELECT * FROM composition_bank WHERE cpt_id=#{cpt_id}")
    public CompositionBankEntity getACompositionByID(int cpt_id);

    public int deleteByPrimaryKey(Integer cpt_id);

    public int insertSelective(CompositionBankEntity record);

    public CompositionBankEntity selectByPrimaryKey(Integer cpt_id);

    public int updateByPrimaryKeySelective(CompositionBankEntity record);

}