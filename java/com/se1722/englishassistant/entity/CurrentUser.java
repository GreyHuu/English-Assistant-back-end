package com.se1722.englishassistant.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * 存储当前登录的用户的基本信息
 */
public class CurrentUser {
    private Integer id;
    //昵称
    private String nick_name;
    //手机号
    private String mobile;
    //    登录时间
    private String loginTime;

    public CurrentUser(Integer id, String nick_name, String mobile, String loginTime) {
        this.id = id;
        this.nick_name = nick_name;
        this.mobile = mobile;
        this.loginTime = loginTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
