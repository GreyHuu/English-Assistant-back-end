package com.se1722.englishassistant.web;
import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.entity.NewsCommentsEntity;
import com.se1722.englishassistant.service.NewsCommentsService;
import com.se1722.englishassistant.utils.RestResponse;
import com.se1722.englishassistant.utils.SessionContent;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/comments")
public class NewsCommentsController {
    // 登录的用户存储如session时的key
    public static final String CURRENT_USER_SESSION = "current_session";
    @Autowired
    private NewsCommentsService newsCommentsService;
    /**
     * 获得全部的p评论
     *
     * @return
     */
    @GetMapping("/get-all-comments")
    public RestResponse getAllComments(){
        List<NewsCommentsEntity> comments = newsCommentsService.findAllComments();
        if (comments!=null) {
            return RestResponse.succuess(comments);
        }else {
            return RestResponse.fail("数据为空");
        }
    }

    /**
     * 获得特定用户的评论
     *
     * @return
     */
    @PostMapping("/get-the-comments")
    public RestResponse getTheComments(HttpServletRequest request){
        //获得用户得user_id
        String sessionId = request.getHeader("Session_Id");// 从 http 请求头中取出 session
        HttpSession session = SessionContent.getSession(sessionId);

        CurrentUser currentUser = (CurrentUser) session.getAttribute(CURRENT_USER_SESSION);
        int id=currentUser.getId();
        List<NewsCommentsEntity> someoneComments =newsCommentsService.findTheComments(id);
        if (someoneComments!=null){
            return RestResponse.succuess(someoneComments);
        }else {
            return RestResponse.fail("数据为空");
        }
    }

    /**
     * 删除用户评论
     *
     * @return
     */
    @PostMapping("/delete-the-comments")
    public RestResponse deleteComments(@NotNull @RequestBody Map<String, String> param){
        Integer c_id= Integer.valueOf(param.get("c_id"));
        int res= newsCommentsService.deleteComments(c_id);
        if (res==1){
            return RestResponse.succuess("删除评论成功");
        }else {
            return RestResponse.fail("删除失败");
        }
    }
    /**
     * 添加用户评论
     *
     * @return
     */
    @PostMapping("/insert-the-comments")
    public RestResponse insertComments(@NotNull @RequestBody NewsCommentsEntity record){
        int res=newsCommentsService.addComments(record);
        if (res==1){
            return RestResponse.succuess("添加成功");
        }else {
            return RestResponse.fail("删除失败");
        }
    }
}
