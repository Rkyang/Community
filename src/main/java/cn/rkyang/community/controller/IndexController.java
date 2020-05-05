package cn.rkyang.community.controller;

import cn.rkyang.community.dto.QuestionDTO;
import cn.rkyang.community.model.User;
import cn.rkyang.community.service.QuestionService;
import cn.rkyang.community.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 主页面控制层
 * @author Rkyang
 */
@Controller
public class IndexController {

    private final QuestionService questionService;

    public IndexController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 主界面
     * @return 主界面
     */
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        User user = SessionUtil.getUserInfo(request);
        if (user != null) {
            request.getSession().setAttribute("user", user);
        }
        List<QuestionDTO> list = questionService.list();
        model.addAttribute("questions", list);
        return "main";
    }
}
