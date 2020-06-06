package com.se1722.englishassistant.entity;

/**
 * @ClassName NewsEntity
 * @Description 
 * @author 15197
 * @date 2020-05-26 14:27:24
 * @version 1.0 
 */
public class NewsEntity {

    private Integer news_id;
    //新闻
    private String news;
    //中文翻译
    private String chinese;
    //标题
    private String title;
    //图片连接
    private String imgurl;
    //用户id
    private Integer user_id;

    public Integer getNews_id() {
        return news_id;
    }
    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }
    public String getNews() {
        return news;
    }
    public void setNews(String news) {
        this.news = news;
    }
    public String getChinese() {
        return chinese;
    }
    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImgurl() {
        return imgurl;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}