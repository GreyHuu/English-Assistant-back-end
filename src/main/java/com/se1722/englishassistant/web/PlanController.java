package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.entity.WordUserPlansEntity;
import com.se1722.englishassistant.service.PlanService;
import com.se1722.englishassistant.utils.RestResponse;
import com.se1722.englishassistant.utils.SessionContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/***********************************************
 类名：PlanController
 功能：单词背诵计划设置初始计划，修改计划，查询计划
 作者：范贤红
 版本：1.0版
 日期：2020/5/29 11:47
 修改日期：
 备注：
 ************************************************/
@Slf4j
@RestController
@RequestMapping("/plan")
public class PlanController {

    @Resource
    private PlanService planService;

    private static Integer user_id;

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
     * 向数组中插入初始化计划每日单词量数据
     * 格式 word_id 1（从哪个单词id开始） 200（每日背诵单词量）
     * type: 单词类型
     * number：每日单词量
     * @return
     */
    @PostMapping("/savePlanDailyNumber/{number}/{type}")
    public RestResponse savePlanDailyNumber(HttpServletRequest request, @PathVariable Integer number ,@PathVariable Integer type){
        user_id = getUser(request);
        if(number <= 0){
            return RestResponse.fail("计划设置出现错误");
        }
        Integer count = planService.savePlanDailyNumber(number, user_id, type);
        if(count != 0){
            return RestResponse.succuess("设置计划成功");
        }
        return RestResponse.fail("计划设置出现错误，请重新设置（每日单词量需要格式正确，不宜过大或过小）");
    }

    /**
     * 查询每日词汇
     * @return
     */
    @GetMapping("/queryDailyWord")
    public RestResponse queryDailyWord(HttpServletRequest request){
        user_id = getUser(request);
        List<WordEntity> wordEntityList = (List<WordEntity>) planService.queryDailyWord(user_id);
        if(wordEntityList.isEmpty()){
           return RestResponse.fail("查询每日词汇失败");
        }

        return RestResponse.succuess("查询每日词汇成功", wordEntityList);
    }

    /**
     * 更新一则背诵信息
     * state为0时，表示没读过的单词。state为1时表示已打卡的单词
     */
    @PostMapping("/updateDailyWordInPlan/{number}/{type}")
    public RestResponse updateDailyWordInPlan(HttpServletRequest request, @PathVariable Integer number ,@PathVariable Integer type){
        user_id = getUser(request);
        log.info(user_id+"正在更新一则背诵信息");
        log.info("删除旧的计划");
        Integer countDel = planService.deleteDailyWordInPlan(user_id);
        System.out.println(countDel);
        log.info("加入新的计划");
        Integer count = planService.savePlanDailyNumber(number, user_id, type);

        if(count>0){
            return RestResponse.succuess("修改计划成功");
        }
        return RestResponse.fail("修改计划失败");
    }

    /**
     * 获得学习数据 学习进度 每日单词 剩余天数
     * @return
     */
    @GetMapping("/getStatiticsById")
    public RestResponse getStatiticsById(HttpServletRequest request){
        user_id = getUser(request);
        List<Object> list = new ArrayList
                <Object>();
        list = planService.getStatiticsById(user_id);
        return RestResponse.succuess("取得学习数据", list);
    }

    /**
     * 更新计划
     * @param plan 更新的计划信息
     * @return
     */
    @PutMapping("/updatePlan")
    public RestResponse updatePlan(@RequestBody WordUserPlansEntity plan, HttpServletRequest request){
        user_id = getUser(request);
        log.info(plan.getUser_id()+"正在更新背诵计划");
        return RestResponse.succuess("更新成功",planService.updatePlan(plan));
    }

    /**
     * 通过id查询用户的计划
     * @return
     */
    @GetMapping("/queryPlanByUserId")
    public RestResponse queryPlanByUserId(HttpServletRequest request){
        user_id = getUser(request);
        return RestResponse.succuess(" 查询成功",planService.queryPlanByUserId(user_id));
    }

    // 完成了当天的背诵任务
    @PostMapping("/updateDailyWordState")
    public RestResponse updateDailyWordState(HttpServletRequest request){
        user_id = getUser(request);
        return RestResponse.succuess(" 今日打卡完成",planService.updateDailyWordState(user_id));
    }

    /**
     * 获得当前上下文的request
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
