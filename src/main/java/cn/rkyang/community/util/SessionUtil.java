package cn.rkyang.community.util;

import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 会话相关工具
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/5 10:11
 */
@Component
public class SessionUtil {

    @Autowired
    private UserMapper userMapper;

    public static SessionUtil sessionUtil;

    @PostConstruct
    public void init() {
        sessionUtil = this;
        sessionUtil.userMapper = this.userMapper;
    }

    /**
     * 从cookie中获取用户信息
     * @param request
     * @return
     */
    public static User getUserInfo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = sessionUtil.userMapper.findByToken(token);
                if (user != null) {
                    return user;
                }
            }
        }
        return null;
    }
}
