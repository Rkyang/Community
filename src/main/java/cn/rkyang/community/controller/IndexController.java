package cn.rkyang.community.controller;

import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.User;
import cn.rkyang.community.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;

/**
 * 主页面控制
 * @author Rkyang
 */
@Controller
public class IndexController {

    private final UserMapper userMapper;

    public IndexController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 主界面
     * @return 主界面
     */
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        User user = SessionUtil.getUserInfo(request);
        if (user != null) {
            request.getSession().setAttribute("user", user);
        }
        return "index";
    }
}
