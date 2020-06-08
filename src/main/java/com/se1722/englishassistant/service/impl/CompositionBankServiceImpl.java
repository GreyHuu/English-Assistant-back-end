package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.CompositionBankDao;
import com.se1722.englishassistant.entity.CompositionBankEntity;
import com.se1722.englishassistant.service.CompositionBankService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作者：姚尊金
 * 日期：2020/6/1 10:38
 */
@Service
public class CompositionBankServiceImpl implements CompositionBankService {

    @Resource
    private CompositionBankDao  compositionBankDao;

    /**
     * 获得题库中的所有作文题
     * @return
     */
    public List<CompositionBankEntity> getAllCompositions() {
        return compositionBankDao.getAllCompositions();
    }

    /**
     * 增加作文引用数
     * @param cpt_id, reference
     * @return
     */
    public int updateReference(int cpt_id, int reference) {
        return compositionBankDao.updateReference(cpt_id, reference);
    }

    /**
     * 通过作文题目的ID查询一篇作文题目
     * @param cpt_id
     * @return
     */
    public CompositionBankEntity getACompositionByID(int cpt_id){
        return compositionBankDao.getACompositionByID(cpt_id);
    }
}