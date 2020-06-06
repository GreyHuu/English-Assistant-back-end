package com.se1722.englishassistant.entity;

/**
 * @ClassName ReadingQuestionEntity
 * @Description 
 * @author 15197
 * @date 2020-06-01 19:35:34
 * @version 1.0 
 */
public class ReadingQuestionEntity {

    private Integer id;
    //问题
    private String question;
    //选项a
    private String optional_a;
    //选项b
    private String optional_b;
    //选项c
    private String optional_c;
    //选项d
    private String optional_d;
    //答案
    private String answer;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getOptional_a() {
        return optional_a;
    }
    public void setOptional_a(String optional_a) {
        this.optional_a = optional_a;
    }
    public String getOptional_b() {
        return optional_b;
    }
    public void setOptional_b(String optional_b) {
        this.optional_b = optional_b;
    }
    public String getOptional_c() {
        return optional_c;
    }
    public void setOptional_c(String optional_c) {
        this.optional_c = optional_c;
    }
    public String getOptional_d() {
        return optional_d;
    }
    public void setOptional_d(String optional_d) {
        this.optional_d = optional_d;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

}