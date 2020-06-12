package com.se1722.englishassistant.entity;

/**
 * @ClassName WordNewEntity
 * @Description status 单词学习状态 0 生词 1 未复习 2 已复习 3 已记住 
 * @author 15197
 * @date 2020-05-26 14:27:24
 * @version 1.0 
 */
public class WordNewEntity {

    private Integer id;
    //用户id
    private Integer user_id;
    private Integer word_id;
    private Integer status;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public Integer getWord_id() {
        return word_id;
    }
    public void setWord_id(Integer word_id) {
        this.word_id = word_id;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

}