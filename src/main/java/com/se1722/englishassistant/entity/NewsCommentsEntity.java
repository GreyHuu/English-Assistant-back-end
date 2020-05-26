package com.se1722.englishassistant.entity;

/**
 * @ClassName NewsCommentsEntity
 * @Description 
 * @author 15197
 * @date 2020-05-26 14:27:24
 * @version 1.0 
 */
public class NewsCommentsEntity {

    //评论内容
    private String comment;
    private Integer user_id;
    private Integer new_id;

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public Integer getNew_id() {
        return new_id;
    }
    public void setNew_id(Integer new_id) {
        this.new_id = new_id;
    }

}