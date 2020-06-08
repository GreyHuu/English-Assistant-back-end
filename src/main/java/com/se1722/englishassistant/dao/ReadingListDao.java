package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.ReadingListEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface ReadingListDao {
    /**
     * 获得某个题目组的练习次数
     *
     * @param id
     * @return
     */
    @Select("select count(*) as num from reading_list where group_id = #{id}")
    int selectCountByGroupID(Integer id);

    /**
     * 获得某个用户的全部的练习次数
     *
     * @param id
     * @return
     */
    @Select("select count(*) as num from reading_list where user_id = #{id}")
    int selectCountByUserID(Integer id);

    /**
     * 获取某个题目组的全部记录
     *
     * @param id
     * @return
     */
    @Select("select *  from reading_list where group_id = #{id}")
    List<ReadingListEntity> selectReadingListByGroupID(Integer id);

    /**
     * 获取某个题目组的最高分数
     *
     * @param id
     * @return
     */
    @Select("select max(score)  from reading_list where group_id = #{id}")
    Double selectMaxScoreByGroupID(Integer id);

    /**
     * 获得某个题目组的最近练习时间
     *
     * @param id
     * @return
     */
    @Select("select max(time)  from reading_list where group_id = #{id}")
    Date selectLastTimeByGroupID(Integer id);

    /**
     * 插入历史记录
     *
     * @param readingListEntity
     * @return
     */
    @Insert("INSERT INTO reading_list(id,group_id,user_id,score,time,during_time) VALUES(#{id},#{group_id},#{user_id},#{score},#{time},#{during_time})")
    Integer insertReadingList(ReadingListEntity readingListEntity);

    /**
     * 获得ReadingListEntity
     *
     * @param id
     * @return
     */
    @Select("select * from reading_list where id = #{id}")
    ReadingListEntity getById(Integer id);

    /**
     * 获得用户的id
     *
     * @param id
     * @return
     */
    @Select("select * from reading_list where user_id = #{id}")
    List<ReadingListEntity> getAllListByUserId(Integer id);
}