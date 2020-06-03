package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.ReadingContentEntity;

import java.util.List;

public interface ReadingContentService {
    List<ReadingContentEntity> selectAll();
    List<ReadingContentEntity> selectAllByGroupId(Integer id);
}
