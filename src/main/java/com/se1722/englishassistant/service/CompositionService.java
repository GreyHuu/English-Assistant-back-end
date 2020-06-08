package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.CompositionEntity;

import java.util.List;

/**
 * 作者：姚尊金
 * 日期：2020/6/1 10:39
 */
public interface CompositionService {

    public int addAComposition(CompositionEntity mycpt);

    public List<CompositionEntity> getAllMyCompositions(int user_id);
}
