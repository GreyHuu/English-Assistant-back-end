package com.se1722.englishassistant.entity;

/**
 * @ClassName ReadingGroupEntity
 * @Description 
 * @author 15197
 * @date 2020-06-01 19:35:34
 * @version 1.0 
 */
public class ReadingGroupEntity {

    private Integer id;
    //组别名称
    private String title;
    //满分
    private Double full_mark;
    private String description;

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

}