package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.CompositionEntity;

/**
 * 作者：姚尊金
 * 日期：2020/6/1 10:39
 */
public interface CompositionService {
    /**
     * 添加一篇我的作文
     */
    public int addACompositionAndCount(CompositionEntity mycpt, int cpt_reference);
}
