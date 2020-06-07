package com.se1722.englishassistant.entity;

/**
 * 类名：CompositionEntity
 * 描述：作文题库实体类
 * 作者：姚尊金
 * 日期：2020/5/27 13:10
 * 版本：1.0
 */
public class CompositionBankEntity {

    //作文ID
    private Integer cpt_id;
    //作文标题
    private String cpt_title;
    //作文题目
    private String cpt_direction;
    //该作文题目被创建的时间
    private String cpt_create_time;
    //范文
    private String cpt_model;
    //引用次数
    private Integer cpt_reference;

    public Integer getCpt_id() {
        return cpt_id;
    }

    public void setCpt_id(Integer cpt_id) {
        this.cpt_id = cpt_id;
    }

    public String getCpt_title() {
        return cpt_title;
    }

    public void setCpt_title(String cpt_title) {
        this.cpt_title = cpt_title;
    }

    public String getCpt_direction() {
        return cpt_direction;
    }

    public void setCpt_direction(String cpt_direction) {
        this.cpt_direction = cpt_direction;
    }

    public String getCpt_create_time() {
        return cpt_create_time;
    }

    public void setCpt_create_time(String cpt_create_time) {
        this.cpt_create_time = cpt_create_time;
    }

    public String getCpt_model() {
        return cpt_model;
    }

    public void setCpt_model(String cpt_model) {
        this.cpt_model = cpt_model;
    }

    public Integer getCpt_reference() {
        return cpt_reference;
    }

    public void setCpt_reference(Integer cpt_reference) {
        this.cpt_reference = cpt_reference;
    }
}