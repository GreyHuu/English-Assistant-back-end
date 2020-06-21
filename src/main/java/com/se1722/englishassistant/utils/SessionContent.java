package com.se1722.englishassistant.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于Session 的分发
 */
@Slf4j
public class SessionContent {
    //    创建一个线程安全的map
    static Map<String, HttpSession> sessionMap
            = Collections.synchronizedMap(new HashMap<String, HttpSession>());

    /**
     * 存储session
     *
     * @param sessionId
     * @param session
     */
    public static void setSession(String sessionId, HttpSession session) {
        sessionMap.put(sessionId, session);
    }

    /**
     * 更新已有的session
     *
     * @param sessionId
     * @param session
     */
    public static void updateSession(String sessionId, HttpSession session) {

        setSession(sessionId, session);
    }

    /**
     * 获得一个空的新的session
     *
     * @return
     */
    public static HttpSession getNewSession() {
        HttpSession session = getRequest().getSession();
        setSession(session.getId(), session);
        return session;
    }

    /**
     * 删除一个存在的session
     *
     * @param sessionId
     */
    public static void removeSession(String sessionId) {
        if (hasSession(sessionId))
            sessionMap.remove(sessionId);
    }

    /**
     * 清空全部的session
     */
    public static void removeAllSession() {
        sessionMap.clear();
    }

    /**
     * 是否包含当前session
     *
     * @param sessionId
     * @return
     */
    public static boolean hasSession(String sessionId) {
        return sessionMap.containsKey(sessionId);
    }

    /**
     * 获取session
     *
     * @param sessionId
     * @return
     */
    public static HttpSession getSession(String sessionId) {
        if (hasSession(sessionId)) {
            log.info("sessionMap中有" + sessionId);
            return sessionMap.get(sessionId);
        } else {
            HttpSession session = getRequest().getSession();
            log.info("sessionMap中无" + sessionId + "，已创建新session，id为" + session.getId());
            return session;
        }

    }

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
