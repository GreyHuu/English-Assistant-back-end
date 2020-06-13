package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.CompositionEntity;

import java.util.List;

/**
 * 作者：姚尊金
 * 日期：2020/6/1 10:39
 */
public interface CompositionService {

    public List<CompositionEntity> getAllMyCompositions(Integer user_id);

    public int addAComposition(CompositionEntity mycpt);

    public int deleteMyCompositionById(Integer mycpt_id);

    public CompositionEntity getAnExistingComposition(Integer mycpt_id);

    public int updateMyComposition(CompositionEntity mycpt);
}
