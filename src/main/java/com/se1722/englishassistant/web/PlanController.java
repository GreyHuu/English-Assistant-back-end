package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.entity.WordUserPlansEntity;
import com.se1722.englishassistant.service.PlanService;
import com.se1722.englishassistant.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private PlanService planService;

    @Autowired
    private UserController userController;

    /**
     * 设置初始计划
     * desc 初始化计划
     * @return
     */
    @PostMapping("/savePlanDefault")
    public RestResponse savePlanDefault(HttpSession session){
        CurrentUser currentUser = null;
        System.out.println(userController.getCurrentUser().getData());
        if( currentUser == null ){
            return RestResponse.fail("用户未登录，初始失败！");
        }
        log.info(currentUser.getNick_name() + "开始插入初始计划");
        Integer count = planService.savePlanDefault(currentUser.getId());
        if (count == 1){
            return RestResponse.succuess("插入初始计划成功");
        }
       return RestResponse.fail("初始化计划失败");
    }

    /**
     * 向数组中插入初始化计划每日单词量数据
     * 格式 word_id 1（从哪个单词id开始） 200（每日背诵单词量）
     * type: 单词类型
     * number：每日单词量
     * @return
     */
    @PostMapping("/savePlanDailyNumber/{number}/{type}")
    public RestResponse savePlanDailyNumber(@PathVariable Integer number ,@PathVariable Integer type){
        Integer user_id = 13;
        Integer count = planService.savePlanDailyNumber(number, user_id, type);
        if(count != 0){
            return RestResponse.succuess("设置计划成功");
        }
        return RestResponse.fail("初始化计划数据出错");
    }

    /**
     * 查询每日词汇
     * @return
     */
    @GetMapping("/queryDailyWord")
    public RestResponse queryDailyWord(){
        Integer user_id = 13;
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
//    @PutMapping("/updateDailyWordInPlan")
//    public RestResponse updateDailyWordInPlan(){
//        Integer user_id = 13;
//        Integer count = planService.updateDailyWordInPlan(user_id, state);
//    }

    /**
     * 更新计划
     * @param plan 更新的计划信息
     * @return
     */
    @PutMapping("/updatePlan")
    public RestResponse updatePlan(@RequestBody WordUserPlansEntity plan){
        log.info(plan.getUser_id()+"正在更新背诵计划");
        return RestResponse.succuess("更新成功",planService.updatePlan(plan));
    }

    /**
     * 通过id查询用户的计划
     * @param user_id 用户id
     * @return
     */
    @GetMapping("/queryPlanByUserId/{user_id}")
    public RestResponse queryPlanByUserId(@PathVariable Integer user_id){
        return RestResponse.succuess(" 查询成功",planService.queryPlanByUserId(user_id));
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
