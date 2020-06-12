package com.se1722.englishassistant.entity;

/**
 * @ClassName ReadingGroupEntity
 * @Description 
 * @author 15197
 * @date 2020-05-26 14:27:24
 * @version 1.0 
 */
public class ReadingGroupEntity {

    private Integer id;
    //组别名称
    private String title;
    //满分
    private Double full_mark;

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

}