package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.NewsEntity;
import com.se1722.englishassistant.service.NewsService;
import com.se1722.englishassistant.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用于处理新闻相关的controller
 */
@Slf4j
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsservice;

    /**
     * 获得全部新闻的信息
     */
    @GetMapping("/get-all-news")
    public RestResponse findAllNews(){
        List<NewsEntity> news =newsservice.findAllNews();
        if (news!=null){
            return RestResponse.succuess(news);
        }else{
            return RestResponse.fail("获取新闻数据失败");
        }
    }
}
