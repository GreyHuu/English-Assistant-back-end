package com.se1722.englishassistant.entity;

/**
 * @ClassName WordEntity
 * @Description 
 * @author 15197
 * @date 2020-05-26 14:27:24
 * @version 1.0 
 */
public class WordEntity {

    private Integer id;
    //单词
    private String englishWord;
    //音标
    private String pa;
    //中文意思
    private String chineseWord;
    //例句1
    private String englishInstance1;
    //例句1翻译
    private String chineseInstance1;
    //例句2
    private String englishInstance2;
    //例句2翻译
    private String chineseInstance2;
    private Integer collect;
    //读音
    private String pron;
    private Integer user_id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEnglishWord() {
        return englishWord;
    }
    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }
    public String getPa() {
        return pa;
    }
    public void setPa(String pa) {
        this.pa = pa;
    }
    public String getChineseWord() {
        return chineseWord;
    }
    public void setChineseWord(String chineseWord) {
        this.chineseWord = chineseWord;
    }
    public String getEnglishInstance1() {
        return englishInstance1;
    }
    public void setEnglishInstance1(String englishInstance1) {
        this.englishInstance1 = englishInstance1;
    }
    public String getChineseInstance1() {
        return chineseInstance1;
    }
    public void setChineseInstance1(String chineseInstance1) {
        this.chineseInstance1 = chineseInstance1;
    }
    public String getEnglishInstance2() {
        return englishInstance2;
    }
    public void setEnglishInstance2(String englishInstance2) {
        this.englishInstance2 = englishInstance2;
    }
    public String getChineseInstance2() {
        return chineseInstance2;
    }
    public void setChineseInstance2(String chineseInstance2) {
        this.chineseInstance2 = chineseInstance2;
    }
    public Integer getCollect() {
        return collect;
    }
    public void setCollect(Integer collect) {
        this.collect = collect;
    }
    public String getPron() {
        return pron;
    }
    public void setPron(String pron) {
        this.pron = pron;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}