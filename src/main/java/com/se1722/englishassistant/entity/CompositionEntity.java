package com.se1722.englishassistant.entity;

/**
 * 类名：CompositionEntity
 * 描述：我的作文实体类
 * 作者：姚尊金
 * 日期：2020/5/27 13:15
 * 版本：1.0
 */
public class CompositionEntity {
    //我的作文ID
    private Integer mycpt_id;
    //作文题目ID
    private Integer cpt_id;
    //用户ID
    private Integer user_id;
    //作文题目标题
    private String cpt_title;
    //我的作文内容
    private String mycpt;
    //作文最近修改的时间
    private String mycpt_create_time;
    //作文字数统计
    private Integer mycpt_word_count;
    //分数
    private Integer mark;

    public Integer getMycpt_id() {
        return mycpt_id;
    }

    public void setMycpt_id(Integer mycpt_id) {
        this.mycpt_id = mycpt_id;
    }

    public Integer getCpt_id() {
        return cpt_id;
    }

    public void setCpt_id(Integer cpt_id) {
        this.cpt_id = cpt_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCpt_title() {
        return cpt_title;
    }

    public void setCpt_title(String cpt_title) {
        this.cpt_title = cpt_title;
    }

    public String getMycpt() {
        return mycpt;
    }

    public void setMycpt(String mycpt) {
        this.mycpt = mycpt;
    }

    public String getMycpt_create_time() {
        return mycpt_create_time;
    }

    public void setMycpt_create_time(String mycpt_create_time) {
        this.mycpt_create_time = mycpt_create_time;
    }

    public Integer getMycpt_word_count() {
        return mycpt_word_count;
    }

    public void setMycpt_word_count(Integer mycpt_word_count) {
        this.mycpt_word_count = mycpt_word_count;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
