package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.CompositionBankEntity;

import java.util.List;

/**
 * 作者：姚尊金
 * 日期：2020/6/1 10:37
 */
public interface CompositionBankService {

    public List<CompositionBankEntity> getAllCompositions();

    public int updateReference(Integer cpt_id, Integer cpt_reference);

    public CompositionBankEntity getACompositionByID(Integer cpt_id);

    public List<CompositionBankEntity> getCompositionQuestionByKeyword(String keyword);
}
