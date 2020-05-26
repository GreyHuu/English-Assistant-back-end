package com.se1722.englishassistant.entity;

/**
 * @ClassName ReadingQuestionGroupEntity
 * @Description 
 * @author 15197
 * @date 2020-05-26 14:27:24
 * @version 1.0 
 */
public class ReadingQuestionGroupEntity {

    private Integer id;
    //文章id
    private Integer reading_id;
    //问题1ID
    private String question_ids;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getReading_id() {
        return reading_id;
    }
    public void setReading_id(Integer reading_id) {
        this.reading_id = reading_id;
    }
    public String getQuestion_ids() {
        return question_ids;
    }
    public void setQuestion_ids(String question_ids) {
        this.question_ids = question_ids;
    }

}