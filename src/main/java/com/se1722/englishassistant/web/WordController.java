package com.se1722.englishassistant.web;

import com.se1722.englishassistant.dao.WordDao;
import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.service.WordService;
import com.se1722.englishassistant.utils.RestResponse;
import com.se1722.englishassistant.utils.SessionContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public Integer user_id ;
    @Autowired
    private WordService wordService;

    // 登录的用户存储如session时的key
    public static final String CURRENT_USER_SESSION = "current_session";

    //拿到用户的id
    private Integer getUser(HttpServletRequest request){
        String sessionId = request.getHeader("Session_Id");// 从 http 请求头中取出 token
        //        获取session
        HttpSession session = SessionContent.getSession(sessionId);
        CurrentUser userInfo = (CurrentUser) session.getAttribute(CURRENT_USER_SESSION);
        log.info("拿到"+userInfo.getNick_name()+"用户信息");
        return userInfo.getId();
    }
    /**
     * 查询词库的单词
     * @param type 单词类型 四六级、考研
     * @return
     */
    @GetMapping("/queryWord/{type}")
    public RestResponse queryWord(@PathVariable int type){
        return RestResponse.succuess("查询成功", wordService.queryWord(type));
    }

    /**
     * 插入生词
     * @param word_id
     * @return
     */
    @PostMapping("/saveWord/{word_id}")
    public RestResponse saveWord(@PathVariable Integer word_id, HttpServletRequest request){
        user_id = getUser(request);
        return RestResponse.succuess("保存生词成功",wordService.saveWord(word_id, user_id));
    }

    /**
     * 删除生词
     * @param word_id
     * @return
     */
    @DeleteMapping("/deleteWord/{word_id}")
    public RestResponse deleteWord(@PathVariable Integer word_id, HttpServletRequest request){
        user_id = getUser(request);
        Integer count = wordService.deleteWord(word_id, user_id);
        if(count == 1){
            return RestResponse.succuess("删除成功");
        }
        return RestResponse.succuess("删除失败");
    }

    /**
     * 查询生词
     * @return
     */
    @GetMapping("/queryNewWord")
    public RestResponse queryNewWord(HttpServletRequest request){
        user_id = getUser(request);
        List<WordEntity> list = new ArrayList<WordEntity>();
        list = (List<WordEntity>)wordService.queryNewWord(user_id);
        if(list.isEmpty()){
            return RestResponse.fail("查询生词失败");
        }
        return RestResponse.succuess("查询生词成功", list);
    }
}
