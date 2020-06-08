package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.CompositionBankDao;
import com.se1722.englishassistant.dao.CompositionDao;
import com.se1722.englishassistant.entity.CompositionEntity;
import com.se1722.englishassistant.service.CompositionBankService;
import com.se1722.englishassistant.service.CompositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * 作者：姚尊金
 * 日期：2020/6/1 10:39
 */
@Service
public class CompositionServiceImpl implements CompositionService {

    @Resource
    private CompositionDao compositionDao;
    @Resource
    private CompositionBankDao compositionBankDao;

    /**
     * 添加一篇我的作文
     * @return
     */

    public int addACompositionAndCount(CompositionEntity mycpt, int cpt_reference) {
        //添加作文
        compositionDao.addAComposition(mycpt);
        //更新引用
        compositionBankDao.countReference(mycpt.getCpt_id(), cpt_reference);

        return 1;
    }
}
