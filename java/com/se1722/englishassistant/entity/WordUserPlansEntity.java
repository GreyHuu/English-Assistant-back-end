package com.se1722.englishassistant.entity;

import java.util.Date;

/**
 * @ClassName WordUserPlansEntity
 * @Description count 背诵单词个数
type 0 四级 1 六级 2 考研
time  计划完成时间
 * @author 15197
 * @date 2020-05-26 14:27:24
 * @version 1.0 
 */
public class WordUserPlansEntity {

    private Integer id;
    private Integer user_id;
    private Integer count;
    private Integer type;
    private Date time;

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
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }

}