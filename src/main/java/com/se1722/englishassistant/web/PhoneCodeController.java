package com.se1722.englishassistant.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.se1722.englishassistant.utils.RestResponse;
import com.se1722.englishassistant.utils.SessionContent;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/phone")
public class PhoneCodeController {
    @Resource
    private RestTemplate restTemplate;
    // 验证码存储key
    private static final String CURRENT_CODE = "current_code";

    /**
     * 给手机发送验证码
     *
     * @param param
     * @return
     */
    @PostMapping("/get-phone-code")
    public RestResponse getPhoneCode(@NotNull @RequestBody Map<String, Object> param, HttpServletRequest httpServletRequest) {
        int code = getRandomCode();
        String url = "http://yzxyzm.market.alicloudapi.com/yzx/verifySms?phone="
                + param.get("phone") + "&templateId=TP18040314&variable=code:" + code;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "APPCODE 1b65d1bc25484848b02e7041253d6527");
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        Map<String, String> responseBody = JSONObject.parseObject(response.getBody(), new TypeReference<Map<String, String>>() {
        });
        if ("00000".equals(responseBody.get("return_code"))) {
            SessionContent.removeAllSession();
            HttpSession session = SessionContent.getNewSession();
            session.setAttribute(CURRENT_CODE, code);
            SessionContent.setSession(session.getId(), session);
            HashMap<String, String> result = new HashMap<>();
            result.put("session", session.getId());
            SessionContent.updateSession(session.getId(), session);
            return RestResponse.succuess("验证码发送成功", result);
        } else
            return RestResponse.fail("发送验证码失败");
    }

    @PostMapping("/compare-code")
    public RestResponse compareCode(@NotNull @RequestBody Map<String, String> params, HttpServletRequest httpServletRequest) {
        int currentCode = Integer.parseInt(params.get("code"));
        String sessionId = httpServletRequest.getHeader("Session_Id");// 从 http 请求头中取出 session
        HttpSession session = SessionContent.getSession(sessionId);
        int code = Integer.parseInt(session.getAttribute(CURRENT_CODE).toString());
        log.info("currentCode" + currentCode + " code " + code);
        if (currentCode == code) {
            return RestResponse.succuess("验证码正确");
        } else {
            return RestResponse.fail("验证码错误");
        }
    }

    /**
     * 获得随机验证码
     *
     * @return
     */
    public int getRandomCode() {
        int max = 1000, min = 9999;
        return (int) (Math.random() * (max - min) + min);
    }

    /**
     * 获得当前上下文的request
     *
     * @return
     */
    private HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }
}
