package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.NewsCommentsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsCommentsDao {
    /**
     * 取出数据库中全部的评论信息
     * @return
     */
    @Select("SELECT * FROM news_comments")
     List<NewsCommentsEntity> getAllComments();
    /**
     * 根据用户id取出数据库中特定用户的评论信息
     * @return
     */
    @Select("SELECT * FROM news_comments WHERE user_id=#{id}")
     List<NewsCommentsEntity> selectByUserid(@Param("id") int id);

    /**
     * 删除特定用户的某项评论
     * @return
     */
    @Delete("DELETE FROM news_comments WHERE c_id=#{c_id}")
    int deleteByPrimaryKey(@Param("c_id") Integer c_id);

    /**
     * 添加特定用户的评论
     * @return
     */
    @Insert("INSERT INTO news_comments (comment,new_id,user_id) VALUES ( #{comment},#{new_id},#{user_id} )")
    int insertComment(NewsCommentsEntity record);

}