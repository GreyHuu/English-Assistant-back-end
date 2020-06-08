package com.se1722.englishassistant.service.impl;

import com.se1722.englishassistant.dao.WordUserPlansDao;
import com.se1722.englishassistant.entity.UserDaliyWordEntity;
import com.se1722.englishassistant.entity.WordEntity;
import com.se1722.englishassistant.entity.WordUserPlansEntity;
import com.se1722.englishassistant.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Resource
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

//    插入背诵每日计划
    public Integer savePlanDailyNumber(Integer number, Integer user_id, Integer type) {
        Integer count = 0;
//        一共有多少条(四级、六级、考研)类型的数据
        Integer total = wordUserPlansDao.select(type);
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

//    查询背诵计划
    public List<WordEntity> queryDailyWord(Integer user_id){
//        将用户计划中未学习的单词的第一个每日计划取出来
        UserDaliyWordEntity userDaliyWordEntity= wordUserPlansDao.selectPlanByUserId(user_id);
        System.out.println(userDaliyWordEntity.toString());
        //        通过计划查询单词
        List<WordEntity> wordEntityList = (List<WordEntity>) wordUserPlansDao.queryDailyWordByPlan(userDaliyWordEntity.getNumber(), userDaliyWordEntity.getWord_id_start(), userDaliyWordEntity.getType());
        return wordEntityList;
    }

//    更新计划
    public Integer updateDailyWordInPlan(Integer number, Integer user_id, Integer type) {
        return wordUserPlansDao.updateDailyWordInPlan(number, user_id, type);
    }
//    删除计划
    public Integer deleteDailyWordInPlan(Integer user_id){
        return wordUserPlansDao.deleteDailyWordInPlan(user_id);
    }

    //拿到学习数据 每日单词量，学习进度
    public List<Object> getStatiticsById(Integer user_id){
        Integer total = wordUserPlansDao.selectCountByState(user_id);
        Integer needStudy = wordUserPlansDao.selectCountByState0(user_id);
        UserDaliyWordEntity userDaliyWordEntity = (UserDaliyWordEntity)wordUserPlansDao.selectPlanByUserId(user_id);
        List<Object> list = new ArrayList<Object>();
        list.add(0, userDaliyWordEntity.getNumber());
        list.add(1,needStudy);
        list.add(2,total);
        return list;
    }

    public Integer updateDailyWordState(Integer user_id) {
        return wordUserPlansDao.updateDailyWordState(user_id);
    }
}

