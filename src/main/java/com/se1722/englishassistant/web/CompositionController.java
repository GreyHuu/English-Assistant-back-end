package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.CompositionBankEntity;
import com.se1722.englishassistant.entity.CompositionEntity;
import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.service.CompositionBankService;
import com.se1722.englishassistant.service.CompositionService;
import com.se1722.englishassistant.utils.RestResponse;
import com.se1722.englishassistant.utils.SessionContent;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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
     * 获取全部作文题目
     * @return
     */
    @GetMapping("/get-all-compositions")
    public RestResponse getAllCompositions(HttpServletRequest request) {
        user_id = getUserID(request);
        List<CompositionEntity> mycptList = compositionService.getAllMyCompositions(user_id);
        List<CompositionBankEntity> cptList = compositionBankService.getAllCompositions();

        for(CompositionBankEntity compositionBankEntity: cptList) {
            compositionBankEntity.setMycpt_id(-1);
        }
        //标记当前用户是否已经提交了当前作文题目
        for(CompositionBankEntity compositionBankEntity: cptList) {
            log.info("compositionBankEntity: "+compositionBankEntity.getCpt_id()+", "+compositionBankEntity.getMycpt_id());
            for(CompositionEntity compositionEntity: mycptList) {
                log.info("compositionEntity= "+compositionEntity.getCpt_id()+", "+compositionEntity.getMycpt_id());
                if(compositionEntity.getCpt_id().equals(compositionBankEntity.getCpt_id())) {
                    compositionBankEntity.setMycpt_id(compositionEntity.getMycpt_id());
                    log.info("匹配成功一次, 设置成功:"+compositionBankEntity.getMycpt_id());
                    break;
                }
            }
        }

        return RestResponse.succuess(cptList);
    }

    /**
     * 添加一篇作文并增加题目的引用数
     * @return
     */
    @ResponseBody
    @PostMapping("/add-a-composition-and-count/{cpt_id}/{cpt_reference}")
    public RestResponse AddCompositionAndCount(@PathVariable("cpt_reference") Integer cpt_reference,
                                               @PathVariable("cpt_id") Integer cpt_id,
                                               @NotNull @RequestBody Map<String, Object>  params,
                                               HttpServletRequest request){
        CompositionEntity compositionEntity = new CompositionEntity();
        String mycpt = params.get("mycpt"). toString();
//        log.info("CompositionController:cpt_reference=" + cpt_reference );
        compositionEntity.setCpt_id(cpt_id);
        compositionEntity.setUser_id(getUserID(request));
        compositionEntity.setMycpt(mycpt);
        compositionEntity.setMycpt_create_time(getCurrentTime());
        compositionEntity.setMycpt_word_count(getWordCount(mycpt));
        compositionEntity.setMark(getScore(getWordCount(mycpt)));

        int num = compositionService.addAComposition(compositionEntity);
        int num2 = compositionBankService.updateReference(cpt_id, cpt_reference);

        if(num == 1 && num2 == 1) {
            return RestResponse.succuess("添加成功");
        } else if(num == 0 || num2 == 0)
        return RestResponse.fail("添加失败");
        else
            return RestResponse.fail("发生未知错误");
    }

    /**
     * 获取当前用户的所有已提交作文
     * @param request
     * @return
     */
    @GetMapping("/get-all-my-compositions")
    public RestResponse getAllMyCompositions(HttpServletRequest request) {
        user_id = getUserID(request);
        List<CompositionEntity> mycptList = compositionService.getAllMyCompositions(user_id);

        for(CompositionEntity mycptListItem: mycptList) {
            mycptListItem.setCpt_title(compositionBankService.getACompositionByID(mycptListItem.getCpt_id()).getCpt_title());
        }

        return RestResponse.succuess(mycptList);
    }

    /**
     * 根据id删除一篇我的作文
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/delete-my-composition")
    public RestResponse deleteMyCompositionById(@NotNull @RequestBody Map<String, Object> params) {
//        log.info("controller中:"+params.get("mycpt_id"));
        int num = compositionService.deleteMyCompositionById(Integer.valueOf(params.get("mycpt_id").toString()));

        if(num == 1) {
            return RestResponse.succuess("删除成功");
        } else if(num == 0)
            return RestResponse.fail("删除失败");
        else
            return RestResponse.fail("发生未知错误");
    }

    /**
     * 获得一篇我的作文信息，包括作文题目、要求和范文
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/get-my-composition")
    public RestResponse getAnExistingComposition(@NotNull @RequestBody Map<String, Object> params) {
        Integer mycpt_id = Integer.valueOf(params.get("mycpt_id").toString());
//        log.info("controller中getAnExistingComposition的参数mycpt_id=" + mycpt_id);

        CompositionEntity compositionEntity = compositionService.getAnExistingComposition(mycpt_id);
        CompositionBankEntity compositionBankEntity = compositionBankService.getACompositionByID(compositionEntity.getCpt_id());

        compositionEntity.setCpt_title(compositionBankEntity.getCpt_title());
        compositionEntity.setCpt_direction(compositionBankEntity.getCpt_direction());
        compositionEntity.setCpt_model(compositionBankEntity.getCpt_model());

        return RestResponse.succuess("success", compositionEntity);
    }

    /**
     * 修改我的作文
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("/update-my-composition")
    public RestResponse updateMyComposition( @NotNull @RequestBody Map<String, Object> params ) {
        Integer mycpt_id = Integer.valueOf(params.get("mycpt_id").toString());
        String mycpt = params.get("mycpt").toString();
        CompositionEntity compositionEntityPre = compositionService.getAnExistingComposition(mycpt_id);

        CompositionEntity compositionEntityNew = new CompositionEntity();
        compositionEntityNew.setMycpt_id(mycpt_id);
        compositionEntityNew.setCpt_id(compositionEntityPre.getCpt_id());
        compositionEntityNew.setUser_id(compositionEntityPre.getUser_id());
        compositionEntityNew.setMycpt(mycpt);
        compositionEntityNew.setMycpt_create_time(getCurrentTime());
        compositionEntityNew.setMycpt_word_count(getWordCount(mycpt));
        compositionEntityNew.setMark(getScore(getWordCount(mycpt)));
        compositionEntityNew.setSubmit_times(compositionEntityPre.getSubmit_times()+1);

        int num = compositionService.updateMyComposition(compositionEntityNew);

        if(num == 1) {
            return RestResponse.succuess("修改成功");
        } else if(num == 0)
            return RestResponse.fail("修改失败");
        else
            return RestResponse.fail("发生未知错误");
    }

    /**
     * 通过关键词搜索作文题
     * @param params
     * @return
     */
    @ResponseBody
    @PostMapping("get-composition-question-keyword")
    public RestResponse getCompositionQuestionByKeyword(@NotNull @RequestBody Map<String, Object> params) {
        String keyword = params.get("keyword").toString();
        return RestResponse.succuess( compositionBankService.getCompositionQuestionByKeyword(keyword));
    }

    /**
     * 获取当前用户的ID
     * @param request
     * @return user_id
     */
    private Integer getUserID(@NotNull HttpServletRequest request){
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
    @NotNull
    private Integer getScore(int wordCount) {
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
     * 获取当前时间
     * @return
     */
    @NotNull
    private String getCurrentTime(){
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间
        return sdf.format(new Date());
    }

    /**
     * 统计作文字数
     * @param mycpt
     * @return
     */
    @Contract(pure = true)
    @NotNull
    private Integer getWordCount(@NotNull String mycpt) {
        return mycpt.split(" ").length;
    }
}