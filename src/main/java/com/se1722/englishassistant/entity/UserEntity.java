package com.se1722.englishassistant.entity;
/**
 * @ClassName CompoitionBankEntity
 * @Description
 * @author 15197
 * @date 2020-05-26 14:27:24
 * @version 1.0
 */

import java.util.Date;

public class UserEntity {
    private Integer id;
    //创建时间
    private Date create_time;
    //密码
    private String password;
    //昵称
    private String nick_name;
    //真实姓名
    private String true_name;
    //年龄
    private Integer age;
    //手机号
    private String mobile;
    //邮箱
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreate_time() {
        return create_time;
    }
}
