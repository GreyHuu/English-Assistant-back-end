package com.se1722.englishassistant.web;

/**
 * 作者：姚尊金
 * 日期：2020/6/7 1:41
 * 写作练习与前端的数据交互
 */

import com.se1722.englishassistant.entity.CompositionBankEntity;
import com.se1722.englishassistant.service.CompositionBankService;
import com.se1722.englishassistant.utils.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/writing")
public class CompositionController {
    @Resource
    private CompositionBankService compositionBankService;

    /**
     *
     */
    @GetMapping("/get-all-compositions")
    public RestResponse getAllCompositions() {
        List<CompositionBankEntity> cptList = compositionBankService.getAllCompositions();
//        List<RestCompositionBankEntity> cptList = new ArrayList<>();

        if(cptList != null) {
//            for()
            return RestResponse.succuess(cptList);
        } else
            return RestResponse.fail("题库为空");

    }
}
