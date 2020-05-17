package cn.rkyang.community.controller;

import cn.rkyang.community.dto.QuestionDTO;
import cn.rkyang.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 问题控制层
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/16 23:02
 */
@Controller
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 问题数据回显
     * @param id 问题id
     * @param model model
     * @return 路径
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id, Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
