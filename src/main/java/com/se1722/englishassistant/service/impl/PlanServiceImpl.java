package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.WordUserPlansDao;
import com.se1722.englishassistant.entity.UserDaliyWordEntity;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.entity.WordUserPlansEntity;
import com.se1722.englishassistant.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "planService")
public class PlanServiceImpl implements PlanService {

    @Autowired
    private WordUserPlansDao wordUserPlansDao;

    public Integer savePlanDefault(Integer user_id) {
        return wordUserPlansDao.save(user_id);
    }

    public Integer updatePlan(WordUserPlansEntity plan) {
        return wordUserPlansDao.update(plan);
    }

    public WordUserPlansEntity queryPlanByUserId(Integer user_id) {
        return wordUserPlansDao.selectByUserId(user_id);
    }

    public Integer savePlanDailyNumber(Integer number, Integer user_id, Integer type) {
        Integer count = 0;
//        一共有多少条(四级、六级、考研)类型的数据
        Integer total = wordUserPlansDao.select(type);
        System.out.println(total);
//        计算可以分成多少天背完
        Integer days = total/number + (total%number == 0 ? 0 : 1);
//        开始向数据库插入每日计划
        for(int i=0; i<days ; i++){
            Integer word_id_start = i*number+1;
            count = wordUserPlansDao.savePlan(user_id,type, word_id_start, number);
        }

        if(total > 0 && count != 0){
            return 1;
        }
        return null;
    }

    public List<WordEntity> queryDailyWord(Integer user_id){
//        将用户计划中未学习的单词的第一个每日计划取出来
        UserDaliyWordEntity userDaliyWordEntity= (UserDaliyWordEntity) wordUserPlansDao.selectPlanByUserId(user_id);
//        通过计划查询单词
        List<WordEntity> wordEntityList = (List<WordEntity>) wordUserPlansDao.queryDailyWordByPlan(userDaliyWordEntity.getNumber(), userDaliyWordEntity.getWord_id_start(), userDaliyWordEntity.getType());

        return wordEntityList;
    }
}
