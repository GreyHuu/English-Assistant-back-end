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
}
