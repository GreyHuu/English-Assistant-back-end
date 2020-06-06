package com.se1722.englishassistant.entity;

import java.util.Date;

/**
 * @ClassName ReadingListEntity
 * @Description 
 * @author 15197
 * @date 2020-06-01 19:35:34
 * @version 1.0 
 */
public class ReadingListEntity {

    private Integer id;
    //阅读id
    private Integer group_id;
    //用户id
    private Integer user_id;
    //分数
    private Double score;
    //提交时间
    private Date time;
    //持续时间
    private String during_time;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getGroup_id() {
        return group_id;
    }
    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public String getDuring_time() {
        return during_time;
    }
    public void setDuring_time(String during_time) {
        this.during_time = during_time;
    }

}