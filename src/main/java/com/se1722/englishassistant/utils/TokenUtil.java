package com.se1722.englishassistant.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.se1722.englishassistant.entity.CurrentUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/***
 * token 工具类
 */
public class TokenUtil {
    /**
     * 根据当前登录用户的用户id和用户手机号通过jwt下发token
     *
     * @param user
     * @return
     */
    public static String getToken(CurrentUser user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
//    生成token
        token = JWT.create().withAudience(user.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getMobile()));
        return token;
    }

    /**
     * 根据token获得userId
     * @return
     */
    public static String getUserIdByToken() {
        String token = Objects.requireNonNull(getRequest()).getHeader("Access-Token");// 从 http 请求头中取出 token
        return JWT.decode(token).getAudience().get(0);
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}
