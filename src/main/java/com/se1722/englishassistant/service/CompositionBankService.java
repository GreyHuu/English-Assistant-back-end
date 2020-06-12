package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.CompositionBankEntity;

import java.util.List;

/**
 * 作者：姚尊金
 * 日期：2020/6/1 10:37
 */
public interface CompositionBankService {
    /**
     * 获得题库中的所有作文题
     * @return
     */
    public List<CompositionBankEntity> getAllCompositions();


}
