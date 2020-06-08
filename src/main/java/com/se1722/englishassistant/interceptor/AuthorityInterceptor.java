package com.se1722.englishassistant.interceptor;

import com.se1722.englishassistant.entity.CurrentUser;
import com.se1722.englishassistant.utils.SessionContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 实现HandlerInterceptor用于对权限进行控制
 */
//如果不想每次都写private  final Logger logger = LoggerFactory.getLogger(当前类名.class); 可以用注解@Slf4j;  用于打印日志
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    // 登录的用户存储如session时的key
    public static final String CURRENT_USER_SESSION = "current_session";
    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();//不拦截的URI

    //    对于不拦截的接口进行添加
    static {
//        登录接口
        NOT_INTERCEPT_URI.add("/users/login");
//        注册接口
        NOT_INTERCEPT_URI.add("/users/register");
//        获得手机验证码的接口
        NOT_INTERCEPT_URI.add("/phone/get-phone-code");
//        验证验证码的接口
        NOT_INTERCEPT_URI.add("/phone/compare-code");
//        错误管理
        NOT_INTERCEPT_URI.add("/error");
//        通过手机验证码登录
        NOT_INTERCEPT_URI.add("/users/login-by-phone");
        //查询全部新闻信息
        NOT_INTERCEPT_URI.add("/news/get-all-news");
        //查询全部评论信息
        NOT_INTERCEPT_URI.add("/comments/get-all-comments");
//查询当前登录用户得所有评论
        NOT_INTERCEPT_URI.add("/comments/get-the-comments");
        //获得当前用户信息
        NOT_INTERCEPT_URI.add("/users/get-current-user");
        //删除评论o
        NOT_INTERCEPT_URI.add("/comments/delete-the-comments");
        //添加评论
        NOT_INTERCEPT_URI.add("/comments/insert-the-comments");
    }

    /**
     * 在请求Controller前进行调用
     * 进行token验证和session验证
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info(Arrays.toString(request.getCookies()));
        if (NOT_INTERCEPT_URI.contains(uri)) {
            log.info("对于" + uri + "不拦截");
            return true;
        }
        String token = request.getHeader("Access-Token");// 从 http 请求头中取出 token
        String sessionId = request.getHeader("Session_Id");// 从 http 请求头中取出 token
//        执行token认证
        if (token == null) {
            response.setStatus(401);
            log.info("当前未登录，已经对" + uri + "进行拦截");
            return false;
        }
//        执行session认证
        log.info("对于" + uri + "进行session拦截");
//        获取session
        HttpSession session = SessionContent.getSession(sessionId);
        CurrentUser userInfo = (CurrentUser) session.getAttribute(CURRENT_USER_SESSION);
        if (userInfo == null) {
            response.setStatus(401);
            log.info("当前未登录，已经对" + uri + "进行session拦截");
            SessionContent.removeAllSession();
            return false;
        }
        return true;
    }

    /**
     * 在Controller处理完请求之后，视图渲染之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求处理之后调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
