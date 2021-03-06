package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.WordDao;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    private WordDao wordDao;

    public List<WordEntity> queryWord(int type) {
        return wordDao.findAll(type);
    }

    public Integer saveWord(Integer word_id, Integer user_id) {
        return wordDao.save(word_id, user_id);
    }

    public Integer deleteWord(Integer word_id, Integer user_id) {
        return wordDao.delete(word_id, user_id);
    }

    public List<WordEntity> queryNewWord(Integer user_id) {
        return wordDao.findAllByUserId(user_id);
    }

    public List<WordEntity> getMeanByWord(String word) {
        List<WordEntity> list = new ArrayList<WordEntity>();
        list = wordDao.getMeanByWord(word);
        return list;
    }
}
