package cn.rkyang.community.controller;

import cn.rkyang.community.dto.QuestionDTO;
import cn.rkyang.community.model.Question;
import cn.rkyang.community.model.User;
import cn.rkyang.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 发布问题控制层
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/4 18:34
 */
@Controller
public class PublishController {

    private final QuestionService questionService;

    public PublishController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 发布问题页面
     * @return 页面路径
     */
    @GetMapping("/publish")
    public String publishQuestionView() {
        return "publish";
    }

    /**
     * 发布问题
     * @param request request
     * @param question 问题模型
     * @param model model
     * @return 发布成功返回的页面
     */
    @PostMapping("/publish")
    public String publishQuestion(HttpServletRequest request, Question question, Model model) {
        //下面的数据回显及三个校验应由前端校验，此处只做测试
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        if (StringUtils.isEmpty(question.getTitle())) {
            model.addAttribute("error", "标题不能为空");
            return "/publish";
        }
        if (StringUtils.isEmpty(question.getDescription())) {
            model.addAttribute("error", "问题描述不能为空");
            return "/publish";
        }
        if (StringUtils.isEmpty(question.getTag())) {
            model.addAttribute("error", "问题标签不能为空");
            return "/publish";
        }
        User user = (User)request.getSession().getAttribute("user");
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }

    /**
     * 修改问题
     * @param id
     * @return
     */
    @GetMapping("/publish/{id}")
    public String editQuestion(@PathVariable(name = "id") Integer id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("title", questionDTO.getTitle());
        model.addAttribute("description", questionDTO.getDescription());
        model.addAttribute("tag", questionDTO.getTag());
        model.addAttribute("id",questionDTO.getId());
        return "/publish";
    }
}
