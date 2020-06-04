package com.se1722.englishassistant.dao;

import com.se1722.englishassistant.entity.UserDaliyWordEntity;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.entity.WordUserPlansEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
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
    public UserDaliyWordEntity selectPlanByUserId(Integer user_id);

    //    通过记录，查单词
    @Select("select * from word where collect =#{type} limit word_id_start,number")
    public List<WordEntity> queryDailyWordByPlan(@Param(value = "number")Integer number, @Param(value = "word_id_start")Integer word_id_start, @Param(value = "type")Integer type);

    //    更新每日记录，将未读过改成已打卡
    @Update("update word_record set state=#{state} where user_id = #{user_id} limit word_id_start,number")
    public List<WordEntity> updateDailyWordInPlan(@Param(value = "state") Integer state, @Param(value = "number")Integer number, @Param(value = "word_id_start")Integer word_id_start, @Param(value = "user_id")Integer user_id);
}