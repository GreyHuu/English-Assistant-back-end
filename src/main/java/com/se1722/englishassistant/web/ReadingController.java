package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.ReadingContentEntity;
import com.se1722.englishassistant.entity.ReadingGroupEntity;
import com.se1722.englishassistant.entity.ReadingQuestionEntity;
import com.se1722.englishassistant.entity.RestReadingGroupEntity;
import com.se1722.englishassistant.service.*;
import com.se1722.englishassistant.utils.RestResponse;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 用于处理阅读模块相关
 */
@Slf4j
@RestController
@RequestMapping("/reading")
public class ReadingController {
    @Resource
    private ReadingService readingService;
    @Resource
    private ReadingListService readingListService;
    @Resource
    private ReadingContentService readingContentService;
    @Resource
    private ReadingQuestionGroupService readingQuestionGroupService;
    @Resource
    private ReadingQuestionService readingQuestionService;

    /**
     * 获得全部的阅读题组
     *
     * @return
     */
    @GetMapping("/get-all-groups")
    public RestResponse getAllGroups() {
        List<ReadingGroupEntity> readingGroupEntities = readingService.getAllReadingGroup();
        List<RestReadingGroupEntity> restReadingGroupEntities = new ArrayList<RestReadingGroupEntity>();
        if (readingGroupEntities != null) {
            // 循环获得关联信息
            for (ReadingGroupEntity readingGroupEntity : readingGroupEntities) {
                int id = readingGroupEntity.getId();
                int times = getGroupAllTimes(id);
                Date datetime = getGroupLastTime(id);
                String time = null;
                if (datetime != null)
                    time = String.valueOf(datetime.getTime());
                Double score = getGroupMaxScore(id);
                RestReadingGroupEntity entity =
                        new RestReadingGroupEntity(id, readingGroupEntity.getTitle(), readingGroupEntity.getFull_mark(),
                                readingGroupEntity.getDescription(), score, time, times);
                restReadingGroupEntities.add(entity);
            }
        }
        return RestResponse.succuess(restReadingGroupEntities);
    }

    /**
     * 根据group_id来获得历史记录中该题目组的练习次数
     *
     * @param id
     * @return
     */
    public Integer getGroupAllTimes(Integer id) {
        return readingListService.selectCountByGroupID(id);
    }

    /**
     * 根据group_id来获得历史记录中该题目组的最近练习时间
     *
     * @param id
     * @return
     */
    public Date getGroupLastTime(Integer id) {
        return readingListService.selectLastTimeByGroupID(id);
    }

    /**
     * 根据group_id来获得历史记录中该题目组的最高分数
     *
     * @param id
     * @return
     */
    public Double getGroupMaxScore(Integer id) {
        return readingListService.selectMaxScoreByGroupID(id);
    }

    /**
     * 模糊查找title
     *
     * @param params
     * @return
     */
    @PostMapping("/search-title")
    public RestResponse searchGroupByTitle(@NotNull @RequestBody Map<String, Object> params) {
        List<ReadingGroupEntity> readingGroupEntities = readingService.searchReadingGroupEntity(params.get("title").toString());
        if (readingGroupEntities != null)
            return RestResponse.succuess(readingGroupEntities);
        return RestResponse.fail("查询出错");
    }

    /**
     * 通过group id获得文章
     *
     * @param params
     * @return
     */
    @PostMapping("/get-contents")
    public RestResponse getContentByGroupId(@NotNull @RequestBody Map<String, Object> params) {
        List<ReadingContentEntity> readingContentEntities = readingContentService.selectAllByGroupId(Integer.valueOf(params.get("id").toString()));
        return RestResponse.succuess(readingContentEntities);
    }

    /**
     * 获得相应文章的问题
     *
     * @param params
     * @return
     */
    @PostMapping("/get-questions")
    public RestResponse getQuestionsByReadingId(@NotNull @RequestBody Map<String, Object> params) {
        String questionIds = readingQuestionGroupService.getQuestionsIdByReadingId(Integer.valueOf(params.get("id").toString()));
        List<String> ids = Arrays.asList(questionIds.split(","));
        List<ReadingQuestionEntity> readingQuestionEntities = new ArrayList<ReadingQuestionEntity>();
        for (String id : ids) {
            ReadingQuestionEntity readingQuestionEntity = readingQuestionService.selectByPrimaryKey(Integer.valueOf(id));
            readingQuestionEntities.add(readingQuestionEntity);
        }
        return RestResponse.succuess(readingQuestionEntities);
    }

    /**
     * 比较答案
     *
     * @param params
     * @return
     */
    @PostMapping("/compare-answers")
    public RestResponse compareGroupAnswer(@NotNull @RequestBody Map<String, Object> params) {
        List<Map<String, Object>> groupsAnswer = (List<Map<String, Object>>) params.get("answers");
        int idExample=Integer.valueOf(groupsAnswer.get(0).get("id").toString());
//        获得题目id
        Integer readingId = readingQuestionGroupService.getGroupIdByQuestionId(idExample);
        if (readingId==null)
            readingId = readingQuestionGroupService.getGroupIdByQuestionId2(idExample);
        if(readingId==null)
            readingId = readingQuestionGroupService.getGroupIdByQuestionId3(idExample);
//        获得题目id
        Integer groupId=readingContentService.selectGroupIdByReadingID(readingId);
        //        获得总分
        Double fullMark = readingService.selectFullMarkById(groupId);
//        算出每道题的分数
        Double eachMark = fullMark / groupsAnswer.size();
//        当前阅读的得分
        Double mark = 0.0;
//        循环比较答案
        for (Map<String, Object> answer : groupsAnswer) {
            Integer id = Integer.valueOf(answer.get("id").toString());
            String an = answer.get("answer").toString();
            Boolean isRight = readingQuestionService.compareQuestion(an, id);
            if (isRight)
                mark += eachMark;
        }
        return RestResponse.succuess(mark);
    }
}
