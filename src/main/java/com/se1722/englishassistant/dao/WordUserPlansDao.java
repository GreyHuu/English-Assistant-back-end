package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.UserDaliyWordEntity;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.entity.WordUserPlansEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

// Mapper可以使mybatis-spring-boot-starter自动映射
@Mapper
@Component
public interface WordUserPlansDao {

    @Insert("insert into word_user_plans(user_id,count,type,time) VALUES (#{user_id},200,0,now())")
    public Integer save(Integer user_id);

    @Update("update word_user_plans set count=#{count},type=#{type} where user_id = #{user_id}")
    public Integer update(WordUserPlansEntity plan);

    @Select("select * from word_user_plans where user_id = #{user_id}")
    public WordUserPlansEntity selectByUserId(Integer user_id);

    @Select("select count(*) from word where collect = #{type}")
    public Integer select(Integer type);

    @Insert("insert into word_record(word_id_start, user_id, state, type, number) values(#{word_id_start}, #{user_id}, 0, #{type}, #{number})")
    public Integer savePlan(@Param(value = "user_id")Integer user_id, @Param(value = "type")Integer type, @Param(value = "word_id_start") Integer word_id_start, @Param(value = "number")Integer number);

    //    查询每日记录中没读过的最前面的一个记录
    @Select("select * from word_record where user_id = #{user_id} and state = 0 limit 0,1")
    public UserDaliyWordEntity selectPlanByUserId(@Param(value = "user_id") Integer user_id);

    //    查询还有多少没背的单词
    @Select("select count(*) from word_record where user_id = #{user_id} and state = 0")
    public Integer selectCountByState0(Integer user_id);

    //    查询计划中一共有多少单词
    @Select("select count(*) from word_record where user_id = #{user_id}")
    public Integer selectCountByState(Integer user_id);

    //    通过记录，查单词
    @Select("select * from word where collect =#{type} limit #{word_id_start},#{number}")
    public List<WordEntity> queryDailyWordByPlan(@Param(value = "number")Integer number, @Param(value = "word_id_start")Integer word_id_start, @Param(value = "type")Integer type);

    //    更新每日记录，更改每日单词量和类型
    @Update("update word_record set number=#{number},type=#{type} where user_id = #{user_id}")
    public Integer updateDailyWordInPlan(@Param(value = "number")Integer number, @Param(value = "user_id")Integer user_id, @Param(value = "type") Integer type);

    //    删除每日记录
    @Delete("delete from word_record where user_id=#{user_id}")
    public Integer deleteDailyWordInPlan(Integer user_id);

    //    更新状态表示已经背完今天的单词
    @Update("Update word_record set state = 1 where user_id=#{user_id} and state=0 limit 1")
    public Integer updateDailyWordState(Integer user_id);


}