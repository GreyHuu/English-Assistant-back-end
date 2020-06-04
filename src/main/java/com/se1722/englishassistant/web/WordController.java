package com.se1722.englishassistant.web;

import com.se1722.englishassistant.dao.WordDao;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.service.WordService;
import com.se1722.englishassistant.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/***********************************************
 类名：WordController
 功能：
 作者：范贤红
 版本：1.0版
 日期：2020/5/29 11:47
 修改日期：
 备注：
 ************************************************/
@Slf4j
@RestController
@RequestMapping("/word")
public class WordController {
    //将所有四级单词查出来放入缓存
    public static final List<WordEntity> fourWordList = new ArrayList<WordEntity>();
    //将所有六级单词查出来放入缓存
    public static final List<WordEntity> sixWordList = new ArrayList<WordEntity>();
    //将所有考研单词查出来放入缓存
    public static final List<WordEntity> postgraduateWordList = new ArrayList<WordEntity>();
    //缓存中的用户信息
    public static final String CURRENT_USER_SESSION = "current_session";
    @Autowired
    private WordService wordService;

    /**
     * 查询词库的单词
     * @param type 单词类型 四六级、考研
     * @return
     */
    @GetMapping("/queryWord/{type}")
    public RestResponse queryWord(@PathVariable int type){
        return RestResponse.succuess("查询成功", wordService.queryWord(type));
    }

    @PostMapping("/saveWord/{word_id}/{user_id}")
    public RestResponse saveWord(@PathVariable Integer word_id, @PathVariable Integer user_id){
        return RestResponse.succuess("保存成功",wordService.saveWord(word_id, user_id));
    }

    @DeleteMapping("/deleteWord/{word_id}/{user_id}")
    public RestResponse deleteWord(@PathVariable Integer word_id, @PathVariable Integer user_id){
        return RestResponse.succuess("删除成功",wordService.deleteWord(word_id, user_id));
    }
}
