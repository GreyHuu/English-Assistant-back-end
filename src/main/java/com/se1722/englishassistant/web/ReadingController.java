package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.*;
import com.se1722.englishassistant.service.*;
import com.se1722.englishassistant.utils.RestResponse;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
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
        List<RestReadingGroupEntity> restReadingGroupEntities = new ArrayList<>();
        if (readingGroupEntities != null) {
            // 循环获得关联信息
            getWholeGroupInformation(readingGroupEntities, restReadingGroupEntities);
        }
        return RestResponse.succuess(restReadingGroupEntities);
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
        List<RestReadingGroupEntity> restReadingGroupEntities = new ArrayList<>();
        if (readingGroupEntities != null) {
            // 循环获得关联信息
            getWholeGroupInformation(readingGroupEntities, restReadingGroupEntities);
            return RestResponse.succuess(restReadingGroupEntities);
        }
        return RestResponse.fail("查询失败");
    }


    /**
     * 根据id删除组别
     *
     * @param params
     * @return
     */
    @PostMapping("/delete-group")
    public RestResponse deleteGroupById(@NotNull @RequestBody Map<String, Object> params) {
        int i = readingService.deleteByPrimaryKey(Integer.valueOf(params.get("id").toString()));
        if (i != 1) {
            return RestResponse.fail("删除失败");
        }
        return RestResponse.succuess();
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
        List<ReadingQuestionEntity> readingQuestionEntities = new ArrayList<>();
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
        int idExample = Integer.valueOf(groupsAnswer.get(0).get("id").toString());
//        获得题目id
        Integer readingId = readingQuestionGroupService.getGroupIdByQuestionId(idExample);
        if (readingId == null)
            readingId = readingQuestionGroupService.getGroupIdByQuestionId2(idExample);
        if (readingId == null)
            readingId = readingQuestionGroupService.getGroupIdByQuestionId3(idExample);
//        获得题目id
        Integer groupId = readingContentService.selectGroupIdByReadingID(readingId);
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

    /**
     * 加入历史记录
     *
     * @param readingListEntity
     * @return
     */
    @PostMapping("/insert-history")
    public RestResponse insertHistory(@NotNull @RequestBody ReadingListEntity readingListEntity) {
        Date time = new Date();
        int id = Integer.parseInt(String.valueOf(time.getTime()).substring(10, 12) + PhoneCodeController.getRandomCode());
        readingListEntity.setTime(time);
        readingListEntity.setId(id);
        int res = readingListService.insertReadingList(readingListEntity);
        if (res != 1)
            return RestResponse.fail("插入历史记录失败");
        ReadingListEntity readingListEntity1 = readingListService.getById(id);
        ReadingGroupEntity readingGroupEntity = readingService.selectByPrimaryKey(readingListEntity.getGroup_id());
        Map<String, Object> re = new HashMap<>();
        re.put("list", readingListEntity1);
        re.put("group", readingGroupEntity);
        re.put("time", readingListEntity.getTime().getTime());
        return RestResponse.succuess(re);
    }

    /**
     * 获得对应用户的历史记录
     *
     * @param params
     * @return
     */
    @PostMapping("/get-all-reading-list")
    public RestResponse getListByUserId(@NotNull @RequestBody Map<String, Object> params) {
        List<ReadingListEntity> readingListEntities = readingListService.getAllListByUserId(Integer.valueOf(params.get("id").toString()));
        RestResponse r = getRestResponseWholeList(readingListEntities);
        if (r != null) return r;
        return RestResponse.fail("查询失败");
    }

    /**
     * 获取阅读历史记录分析
     *
     * @param params
     * @return
     */
    @PostMapping("/get-reading-list-data")
    public RestResponse getListData(@NotNull @RequestBody Map<String, Object> params) {
        int id = Integer.valueOf(params.get("id").toString());
        List<ReadingListEntity> readingListEntities = readingListService.getAllListByUserId(id);
        if (readingListEntities != null) {
            //        次数
            int times = readingListService.selectCountByUserID(id);
//        平均用时
            long during_avg = 0;
            double score_avg = 0.0;
            for (ReadingListEntity readingListEntity : readingListEntities) {
                during_avg += Long.parseLong(readingListEntity.getDuring_time());
                score_avg += readingListEntity.getScore();
            }
            during_avg = during_avg / readingListEntities.size();
            score_avg = score_avg / readingListEntities.size();
            Map<String, Object> result = new HashMap<>();
            result.put("times", times);
            result.put("during", during_avg);
            result.put("score", score_avg);
            return RestResponse.succuess(result);
        }
        return RestResponse.fail("查询失败");
    }

    /**
     * 根据题目组的名称搜索历史记录
     *
     * @param params
     * @return
     */
    @PostMapping("/search-list")
    public RestResponse searchListData(@NotNull @RequestBody Map<String, Object> params) {
        List<ReadingGroupEntity> readingGroupEntities = readingService.searchReadingGroupEntity(params.get("title").toString());
        List<ReadingListEntity> readingListEntities = new ArrayList<>();
        if (readingGroupEntities != null) {
            for (ReadingGroupEntity readingGroupEntity : readingGroupEntities) {
                List<ReadingListEntity> temp = null;
                int group_id = readingGroupEntity.getId();
                temp = readingListService.selectReadingListByGroupID(group_id);
                readingListEntities.addAll(temp);
            }
            RestResponse r = getRestResponseWholeList(readingListEntities);
            if (r != null) return r;
        }
        return RestResponse.fail("查询失败");
    }

    /**
     * 获得完整的list返回结果
     *
     * @param readingListEntities
     * @return
     */
    @Nullable
    private RestResponse getRestResponseWholeList(List<ReadingListEntity> readingListEntities) {
        List<Map<String, Object>> r = new ArrayList<>();
        Map<String, Object> result = null;
        if (readingListEntities != null) {
            for (ReadingListEntity readingListEntity : readingListEntities) {
                result = new HashMap<>();
                ReadingGroupEntity readingGroupEntity = readingService.selectByPrimaryKey(readingListEntity.getGroup_id());
                result.put("list", readingListEntity);
                result.put("time", readingListEntity.getTime().getTime());
                result.put("group", readingGroupEntity);
                r.add(result);
            }

            return RestResponse.succuess(r);
        }
        return null;
    }

    /**
     * 遍历readingGroupEntities来完整的组别信息
     *
     * @param readingGroupEntities
     * @param restReadingGroupEntities
     */
    private void getWholeGroupInformation(@NotNull List<ReadingGroupEntity> readingGroupEntities, List<RestReadingGroupEntity> restReadingGroupEntities) {
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
}
