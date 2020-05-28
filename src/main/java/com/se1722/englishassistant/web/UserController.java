package com.se1722.englishassistant.web;

import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.entity.UserEntity;
import com.se1722.englishassistant.service.UserService;
import com.se1722.englishassistant.utils.BeijingTime;
import com.se1722.englishassistant.utils.RestResponse;
import com.se1722.englishassistant.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private UserService userService;

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
    public RestResponse userLogin(@RequestBody Map<String, Object> param) {   // 使用RequestBody和map来接收json数据
        log.info("用户手机号为 " + param.get("phone") + " 的用户正在登录");
        UserEntity userEntity = userService.findUserByPhone(param.get("phone").toString());
        if (userEntity != null) {
//         检查匹配加密后的密码
            if (BCrypt.checkpw(param.get("password").toString(), userEntity.getPassword())) {
//                当前登录的用户
                CurrentUser currentUser =
                        new CurrentUser(userEntity.getId(), userEntity.getNick_name(), userEntity.getMobile(), BeijingTime.getChinaTime());
                HttpSession session = getRequest().getSession();
                session.setAttribute(CURRENT_USER_SESSION, currentUser);
//                生成token
                String token = TokenUtil.getToken(currentUser);
//                放入返回的map中
                HashMap<String, String> result = new HashMap<>();
                result.put("userName", currentUser.getNick_name());
                result.put("token", token);
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
    public RestResponse getCurrentUser() {
        HttpSession session = getRequest().getSession();
        CurrentUser currentUser = (CurrentUser) session.getAttribute(CURRENT_USER_SESSION);
        if (currentUser == null)
            return RestResponse.fail("当前无登录的用户");
        else
            return RestResponse.succuess(currentUser);
    }

    /**
     * 注册用户
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/register")
    public RestResponse registerUser(@RequestBody UserEntity userEntity) {
        UserEntity user = userService.findUserByPhone(userEntity.getMobile());
        if (user != null)
            return RestResponse.fail("手机号已经注册，请直接登录");
//        设置登录时间
        userEntity.setCreate_time(new Date());
//        使用jBCrypt对密码进行加密  密码加密后重新放入对象中
        userEntity.setPassword(BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt(4)));
        int res = userService.addUser(userEntity);
        if (res == 1) {
            return RestResponse.succuess("注册成功，请登录");
        }
        return RestResponse.fail();
    }

    /**
     * 获得当前上下文的request
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
