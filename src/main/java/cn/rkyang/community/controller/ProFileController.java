package cn.rkyang.community.controller;

import cn.rkyang.community.dto.PageDTO;
import cn.rkyang.community.model.User;
import cn.rkyang.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 我的问题页面右侧导航栏路径控制层
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/11 21:18
 */
@Controller
public class ProFileController {

    private final QuestionService questionService;

    public ProFileController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 动态获取信息
     * @param action 路径
     * @param model model
     * @param request request
     * @param page 当前页
     * @param size 页条数
     * @return 路径
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action, Model model, HttpServletRequest request,
                          @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "5")Integer size) {
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "My questions");
        }else if ("repies".equals(action)) {
            model.addAttribute("section", "repies");
            model.addAttribute("sectionName", "Latest Reply");
        }
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        PageDTO pageDTO = questionService.findByUser(user.getId(), page, size);
        model.addAttribute("pages",pageDTO);
        return "profile";
    }
}
