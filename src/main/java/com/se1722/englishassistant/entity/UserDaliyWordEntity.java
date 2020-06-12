package com.se1722.englishassistant.entity;

/***********************************************
 类名：UserDaliyWordEntity
 功能：
 作者：范贤红
 版本：1.0版
 日期： 22:53
 修改日期：
 备注：
 ************************************************/
public class UserDaliyWordEntity {
    private Integer id;
    private Integer word_id_start;
    private Integer user_id;
    private Integer state;
    private Integer type;
    private Integer number;

    @Override
    public String toString() {
        return "UserDaliyWordEntity{" +
                "id=" + id +
                ", word_id_start=" + word_id_start +
                ", user_id=" + user_id +
                ", state=" + state +
                ", type=" + type +
                ", number=" + number +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWord_id_start() {
        return word_id_start;
    }

    public void setWord_id_start(Integer word_id_start) {
        this.word_id_start = word_id_start;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
