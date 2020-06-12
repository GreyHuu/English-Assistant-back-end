package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.entity.UserEntity;
import com.se1722.englishassistant.service.PlanService;
import com.se1722.englishassistant.service.UserService;
import com.se1722.englishassistant.utils.BeijingTime;
import com.se1722.englishassistant.utils.RestResponse;
import com.se1722.englishassistant.utils.SessionContent;
import com.se1722.englishassistant.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 用于处理用户相关的controller
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    // 登录的用户存储如session时的key
    public static final String CURRENT_USER_SESSION = "current_session";

    @Resource
    private UserService userService;

    @Resource
    private PlanService planService;

    /**
     * 获得全部的用户
     *
     * @return
     */
    @GetMapping("/get-all-users")
    public List<UserEntity> getAllUser() {
        return userService.findAllUser();
    }

    /**
     * 登录
     *
     * @param param
     * @return
     */
    @PostMapping("/login")
    public RestResponse userLogin(@NotNull @RequestBody Map<String, Object> param, HttpServletResponse response) {   // 使用RequestBody和map来接收json数据
        log.info("用户手机号为 " + param.get("phone") + " 的用户正在登录");
        UserEntity userEntity = userService.findUserByPhone(param.get("phone").toString());
        if (userEntity != null) {
//         检查匹配加密后的密码
            if (BCrypt.checkpw(param.get("password").toString(), userEntity.getPassword())) {
//                当前登录的用户
                CurrentUser currentUser =
                        new CurrentUser(userEntity.getId(), userEntity.getNick_name(), userEntity.getMobile(), BeijingTime.getChinaTime());
//               清空全部的Session
                SessionContent.removeAllSession();
                HttpSession session = SessionContent.getNewSession();
                session.setAttribute(CURRENT_USER_SESSION, currentUser);
//                 存储session
                SessionContent.updateSession(session.getId(), session);
//                生成token
                String token = TokenUtil.getToken(currentUser);
//                放入返回的map中
                HashMap<String, String> result = new HashMap<String, String>();
                result.put("userName", currentUser.getNick_name());
                result.put("token", token);
                result.put("session", session.getId());
                return RestResponse.succuess("登录成功", result);
            }
        } else {
            return RestResponse.fail("该手机号尚未注册");
        }
        return RestResponse.fail("密码错误");
    }

    /**
     * 获得当前登录的用户
     *
     * @return
     */
    @GetMapping("/get-current-user")
    public RestResponse getCurrentUser(HttpServletRequest request) {
        String sessionId = request.getHeader("Session_Id");// 从 http 请求头中取出 session
        HttpSession session = SessionContent.getSession(sessionId);
        CurrentUser currentUser = (CurrentUser) session.getAttribute(CURRENT_USER_SESSION);
        if (currentUser == null)
            return RestResponse.fail("当前无登录的用户");
        else
            return RestResponse.succuess(currentUser);
    }

    /**
     * 手机验证码登录
     *
     * @param param
     * @return
     */
    @PostMapping("/login-by-phone")
    public RestResponse loginByPhone(@NotNull @RequestBody Map<String, String> param) {
        UserEntity userEntity = userService.findUserByPhone(param.get("phone"));
        CurrentUser currentUser =
                new CurrentUser(userEntity.getId(), userEntity.getNick_name(), userEntity.getMobile(), BeijingTime.getChinaTime());
        if (currentUser != null) {
            SessionContent.removeAllSession();
            HttpSession session = SessionContent.getNewSession();
            session.setAttribute(CURRENT_USER_SESSION, currentUser);
//                生成token
            String token = TokenUtil.getToken(currentUser);
//                放入返回的map中
            HashMap<String, String> result = new HashMap<String, String>();
            result.put("userName", currentUser.getNick_name());
            result.put("token", token);
            result.put("session", session.getId());
            SessionContent.updateSession(session.getId(), session);
            return RestResponse.succuess("登录成功", result);
        } else {
            return RestResponse.fail("当前手机号未注册，请先注册");
        }

    }

    /**
     * 注销登录
     *
     * @return
     */
    @GetMapping("/logout")
    public RestResponse userLogout() {
        SessionContent.removeAllSession();
        return RestResponse.succuess("注销成功");
    }

    /**
     * 注册用户
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/register")
    public RestResponse registerUser(@NotNull @RequestBody UserEntity userEntity) {
        SessionContent.removeAllSession();
        UserEntity user = userService.findUserByPhone(userEntity.getMobile());
        if (user != null)
            return RestResponse.fail("手机号已经注册，请直接登录");
//        设置登录时间
        userEntity.setCreate_time(new Date());
//        使用jBCrypt对密码进行加密  密码加密后重新放入对象中
        userEntity.setPassword(BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt(4)));
        int res = userService.addUser(userEntity);
        UserEntity user1 = userService.findUserByPhone(userEntity.getMobile());
        log.info("注册成功");
        log.info("初始化背诵计划");
        int rs = planService.savePlanDailyNumber(200, user1.getId(), 0);
        if (res == 1 && rs == 1) {
            return RestResponse.succuess("注册成功，请登录");
        }
        return RestResponse.fail();
    }
}
