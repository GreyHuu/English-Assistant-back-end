package com.se1722.englishassistant.entity;

import java.util.Date;

public class RestReadingGroupEntity {
    private Integer id;
    //组别名称
    private String title;
    //满分
    private Double full_mark;
    //    描述
    private String description;
    //    最高分
    private Double score;
    //最近的提交时间
    private String time;
    //    练习次数
    private Integer times;

    public RestReadingGroupEntity(Integer id, String title, Double full_mark, String description, Double score, String time, Integer times) {
        this.id = id;
        this.title = title;
        this.full_mark = full_mark;
        this.description = description;
        this.score = score;
        this.time = time;
        this.times = times;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getFull_mark() {
        return full_mark;
    }

    public void setFull_mark(Double full_mark) {
        this.full_mark = full_mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}
