package com.se1722.englishassistant.service;

import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.entity.WordUserPlansEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlanService {
	
    Integer savePlanDefault(Integer user_id);

    Integer updatePlan(WordUserPlansEntity plan);

    WordUserPlansEntity queryPlanByUserId(Integer user_id);

    Integer savePlanDailyNumber( Integer number, Integer user_id, Integer type);

    List<WordEntity> queryDailyWord(Integer user_id);

    Integer updateDailyWordInPlan(Integer number, Integer user_id, Integer type);

    List<Object> getStatiticsById(Integer user_id);

    Integer updateDailyWordState(Integer user_id);

    Integer deleteDailyWordInPlan(Integer user_id);
}
