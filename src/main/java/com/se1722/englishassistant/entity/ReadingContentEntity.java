package com.se1722.englishassistant.entity;

/**
 * @ClassName ReadingContentEntity
 * @Description 
 * @author 15197
 * @date 2020-06-01 19:35:34
 * @version 1.0 
 */
public class ReadingContentEntity {

    private Integer id;
    //文章内容
    private String content;
    //所属阅读组的组id
    private Integer group_id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getGroup_id() {
        return group_id;
    }
    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

}