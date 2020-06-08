package com.se1722.englishassistant.web;

import com.alibaba.fastjson.JSON;
import com.se1722.englishassistant.entity.CompositionBankEntity;
import com.se1722.englishassistant.entity.CompositionEntity;
import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.service.CompositionBankService;
import com.se1722.englishassistant.service.CompositionService;
import com.se1722.englishassistant.utils.RestResponse;
import com.se1722.englishassistant.utils.SessionContent;
import jdk.nashorn.internal.lookup.MethodHandleFactory;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/*
 * 作者：姚尊金
 * 日期：2020/6/7 1:41
 * 写作练习与前端的数据交互
 */
@Slf4j
@RestController
@RequestMapping("/writing")
public class CompositionController {
    // 获取当前session
    private static final String CURRENT_USER_SESSION = "current_session";
    //登录后的用户ID
    private Integer user_id ;

    @Resource
    private CompositionBankService compositionBankService;
    @Resource
    private CompositionService compositionService;

    /**
     * 获取当前用户的ID
     * @param request
     * @return user_id
     */
    private Integer getUserID(HttpServletRequest request){
        String sessionId = request.getHeader("Session_Id");
        //获取当前session
        HttpSession session = SessionContent.getSession(sessionId);
        //获取当前用户信息
        CurrentUser user = (CurrentUser) session.getAttribute(CURRENT_USER_SESSION);

        return user.getId();
    }

    /**
     * 计算分数
     * @param wordCount
     * @return score
     */
    public Integer getScore(int wordCount) {
        int baseScore = 70;
        int randomScore = new Random().nextInt(10) + 1;

        if(wordCount <= 100)
            return baseScore + randomScore + new Random().nextInt(5) + 1;
        else if (wordCount <= 150)
            return baseScore + randomScore + new Random().nextInt(10) + 1;
        else if (wordCount <= 250)
            return baseScore + randomScore + new Random().nextInt(15) + 1;
        else if (wordCount <= 400)
            return baseScore + randomScore + new Random().nextInt(20) + 1;
        else
            return baseScore + randomScore + new Random().nextInt(5) + 1;

    }
    /**
     *获取全部作文题目
     */
    @GetMapping("/get-all-compositions")
    public RestResponse getAllCompositions() {
        List<CompositionBankEntity> cptList = compositionBankService.getAllCompositions();

        if(cptList != null) {
            return RestResponse.succuess(cptList);
        } else
            return RestResponse.fail("题库为空");
    }

    /**
     * 添加一篇作文并增加题目的引用数
     * @return
     */
//    当前只能接收到         cpt_reference  和  compositionEntity.cpt_id的参数            待修改
    @ResponseBody
    @PostMapping("/add-a-composition-and-count/{cpt_reference}")
    public RestResponse AddCompositionAndCount(@PathVariable Integer cpt_reference,
                                               @NotNull @RequestBody CompositionEntity compositionEntity,
                                               HttpServletRequest request){
        user_id = getUserID(request);
        compositionEntity.setUser_id(user_id);
        compositionEntity.setMark(getScore(compositionEntity.getMycpt_word_count()));
        System.out.println("cpt="+compositionEntity.toString());
        System.out.println("ref="+cpt_reference);

        int num = compositionService.addAComposition(compositionEntity);
        int num2 = compositionBankService.updateReference(compositionEntity.getCpt_id(), cpt_reference);

        if(num == 1 && num2 == 1) {
            return RestResponse.succuess("添加成功");
        } else if(num == 0 || num2 == 0)
        return RestResponse.fail("添加失败");
        else
            return RestResponse.fail("发生未知错误");
    }

    @GetMapping("/get-all-my-compositions")
    public RestResponse getAllMyCompositions(HttpServletRequest request) {
        user_id = getUserID(request);
        List<CompositionEntity> mycptList = compositionService.getAllMyCompositions(user_id);

        for(CompositionEntity mycptListItem: mycptList) {
            mycptListItem.setCpt_title(compositionBankService.getACompositionByID(mycptListItem.getCpt_id()).getCpt_title());
        }

        return RestResponse.succuess(mycptList);
    }

}