package com.se1722.englishassistant.service;



public interface ReadingQuestionGroupService {
    String getQuestionsIdByReadingId(Integer id);
    Integer getGroupIdByQuestionId(Integer id);
    Integer getGroupIdByQuestionId2(Integer id);

    Integer getGroupIdByQuestionId3(Integer id);
}
