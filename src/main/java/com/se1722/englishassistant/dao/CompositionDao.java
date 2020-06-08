package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.CompositionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 作者：姚尊金
 * 日期：2020/5/29 9:26
 */
@Mapper
public interface CompositionDao {

    /**
     * @description 查询当前用户的所有保存了的作文
     * @param user_id
     * @return List<CompositionEntity>
     */
    @Select("SELECT * FROM composition WHERE user_id=#{user_id}")
    public List<CompositionEntity> getAllMyCompositions(int user_id);

    /**
     * @description 通过我的作文id查询一篇作文
     * @param mycpt_id
     * @return List<CompositionEntity>
     */
    @Select("SELECT * FROM composition WHERE mycpt_id=#{mycpt_id}")
    public CompositionEntity getAnExisitingComposition(int mycpt_id);

    /**
     *@description 添加一篇作文
     * @param mycpt
     * @return
     */
    @Insert("INSERT INTO composition(cpt_id, user_id, mycpt, mycpt_create_time, mycpt_word_count) " +
            "values(#{mycpt.cpt_id}, #{mycpt.user_id}, #{mycpt}, #{mycpt_create_time}, #{mycpt_word_count})")
    public int addAComposition(CompositionEntity mycpt);

    /**
     *@description 删除一篇作文
     * @param mycpt
     * @return
     */
    @Delete("DELETE composition WHERE mycpt_id=#{mycpt.mycpt_id}")
    public int deleteAComposition(CompositionEntity mycpt);

    /**
     *@description 更新作文内容
     * @param mycpt
     * @return
     */
    @Update("UPDATE composition SET mycpt=#{mycpt.mycpt}, " +
            "mycpt_create_time=#{mycpt.mycpt_create_time} WHERE mycpt_id=#{mycpt.mycpt_id}")
    public int updateMycptContent(CompositionEntity mycpt);

    /**
     *@description 通过作文题目ID和用户Id查询一篇作文
     * @param cpt_id
     * @param user_id
     * @return
     */
    @Select("SELECT * FROM composition WHERE cpt_id=#{cpt_id} AND user_id=#{user_id}")
    public List<CompositionEntity> getAnExistingCompositionByCptIdAndUser_id(int cpt_id, int user_id);

}
