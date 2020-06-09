package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.CompositionBankDao;
import com.se1722.englishassistant.dao.CompositionDao;
import com.se1722.englishassistant.entity.CompositionEntity;
import com.se1722.englishassistant.service.CompositionBankService;
import com.se1722.englishassistant.service.CompositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 作者：姚尊金
 * 日期：2020/6/1 10:39
 */
@Service
public class CompositionServiceImpl implements CompositionService {

    @Resource
    private CompositionDao compositionDao;

    /**
     * 查询我的所有作文
     * @return
     */
    @Override
    public List<CompositionEntity> getAllMyCompositions(Integer user_id) {
        return compositionDao.getAllMyCompositions(user_id);
    }

    /**
     * 添加一篇我的作文
     * @return
     */
    public int addAComposition(CompositionEntity mycpt) {
        return compositionDao.addAComposition(mycpt);
    }

    /**
     * 删除一篇我的作文
     * @param mycpt_id
     * @return
     */
    @Override
    public int deleteMyCompositionById(Integer mycpt_id) {
        return compositionDao.deleteMyCompositionById(mycpt_id);
    }

}
