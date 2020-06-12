package com.se1722.englishassistant.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;

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
}