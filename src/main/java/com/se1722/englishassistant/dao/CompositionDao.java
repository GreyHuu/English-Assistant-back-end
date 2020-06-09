package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.CompositionEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 作者：姚尊金
 * 日期：2020/5/29 9:26
 */
@Mapper
@Component
public interface CompositionDao {

    /**
     * @param user_id
     * @return List<CompositionEntity>
     * @description 查询当前用户的所有保存了的作文
     */
    @Select("SELECT * FROM composition WHERE user_id=#{user_id}")
    public List<CompositionEntity> getAllMyCompositions(Integer user_id);

    /**
     * @param mycpt
     * @return
     * @description 添加一篇作文
     */
    @Insert("INSERT INTO composition(cpt_id, user_id, mycpt, mycpt_create_time, mycpt_word_count, mark) " +
            "values(#{cpt_id}, #{user_id}, #{mycpt}, #{mycpt_create_time}, #{mycpt_word_count}, #{mark})")
    public int addAComposition(CompositionEntity mycpt);

    /**
     * 删除一篇我的作文
     *
     * @param mycpt_id
     * @return
     */
    @Delete("DELETE FROM composition WHERE mycpt_id=#{mycpt_id}")
    public int deleteMyCompositionById(Integer mycpt_id);

    /**
     * 通过我的作文id查询一篇作文
     * @param mycpt_id
     * @return
     */
    @Select("SELECT * FROM composition WHERE mycpt_id=#{mycpt_id}")
    public CompositionEntity getAnExistingComposition(Integer mycpt_id);

    /**
     *@description 更新作文内容
     * @param mycpt
     * @return
     */
    @Update("UPDATE composition SET mycpt=#{mycpt}, mycpt_create_time=#{mycpt_create_time}, " +
            "mycpt_word_count=#{mycpt_word_count}, mark=#{mark}, submit_times=#{submit_times} WHERE mycpt_id=#{mycpt_id}")
    public int updateMyComposition(CompositionEntity mycpt);

    /**
     *@description 通过作文题目ID和用户Id查询一篇作文
     * @param cpt_id
     * @param user_id
     * @return
     */
    @Select("SELECT * FROM composition WHERE cpt_id=#{cpt_id} AND user_id=#{user_id}")
    public List<CompositionEntity> getAnExistingCompositionByCptIdAndUser_id(int cpt_id, int user_id);

}
