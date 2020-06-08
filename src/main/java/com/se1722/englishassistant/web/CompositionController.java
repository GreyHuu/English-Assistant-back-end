package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.CompositionBankEntity;
import com.se1722.englishassistant.entity.CompositionEntity;
import com.se1722.englishassistant.service.CompositionBankService;
import com.se1722.englishassistant.service.CompositionService;
import com.se1722.englishassistant.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/*
 * 作者：姚尊金
 * 日期：2020/6/7 1:41
 * 写作练习与前端的数据交互
 */
@Slf4j
@RestController
@RequestMapping("/writing")
public class CompositionController {
    @Resource
    private CompositionBankService compositionBankService;
    @Resource
    private CompositionService compositionService;

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
//    400错误
//    form-data、x-www-form-urlencoded：需要使用@RequestParam
//    application/json： 需要使用@RequestBody
    @PostMapping("/add-a-composition-and-count")
//    public RestResponse AddCompositionAndCount(CompositionEntity mycpt, Integer cpt_reference){
    public RestResponse AddCompositionAndCount(@RequestBody String jsonStr){
        System.out.println(jsonStr);
//        int num = compositionService.addACompositionAndCount(
//                (CompositionEntity) map.get("mycpt"),
//                (Integer) map.get("cpt_reference"));
         int num=0;

        if(num == 1) {
            return RestResponse.succuess("添加成功");
        } else if(num == 0)
        return RestResponse.fail("添加失败");
        else
            return RestResponse.fail("发生未知错误");
    }
}
