package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.WordEntity;

import java.util.List;

public interface WordService {
    List<WordEntity> queryWord(int type);
    Integer saveWord(Integer word_id, Integer user_id);
    Integer deleteWord(Integer word_id, Integer user_id);
    List<WordEntity> queryNewWord(Integer user_id);
    List<WordEntity> getMeanByWord(String word);
}
