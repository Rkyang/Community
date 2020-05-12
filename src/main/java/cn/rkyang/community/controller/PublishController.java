package cn.rkyang.community.controller;

import cn.rkyang.community.mapper.QuestionMapper;
import cn.rkyang.community.model.Question;
import cn.rkyang.community.model.User;
import cn.rkyang.community.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

    private final QuestionMapper questionMapper;

    public PublishController(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
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
        User user = SessionUtil.getUserInfo(request);
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
        question.setCreator(user.getId());
        question.setCreateTime(System.currentTimeMillis());
        question.setModifiedTime(question.getCreateTime());
        questionMapper.create(question);
        return "redirect:/";
    }
}
