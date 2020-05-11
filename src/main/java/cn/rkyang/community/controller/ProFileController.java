package cn.rkyang.community.controller;

import cn.rkyang.community.dto.PageDTO;
import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.User;
import cn.rkyang.community.service.QuestionService;
import cn.rkyang.community.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 我的问题页面右侧导航栏路径控制
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/11 21:18
 */
@Controller
public class ProFileController {

    private final UserMapper userMapper;

    private final QuestionService questionService;

    public ProFileController (UserMapper userMapper, QuestionService questionService) {
        this.userMapper = userMapper;
        this.questionService = questionService;
    }

    /**
     * 动态获取信息
     * @param action
     * @param model
     * @param request
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model, HttpServletRequest request,
                          @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer size) {
        User user = SessionUtil.getUserInfo(request);
        if (user == null) {
            //这里是因为经常访问GitHub失败，所以设死
            user = userMapper.findById(197);
        }
        request.getSession().setAttribute("user", user);
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "My questions");
        }else if ("repies".equals(action)) {
            model.addAttribute("section", "repies");
            model.addAttribute("sectionName", "Latest Reply");
        }
        PageDTO pageDTO = questionService.findByUser(user.getId(), page, size);
        model.addAttribute("pages",pageDTO);
        return "profile";
    }
}
