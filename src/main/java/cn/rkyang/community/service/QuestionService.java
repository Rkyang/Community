package cn.rkyang.community.service;

import cn.rkyang.community.dto.PageDTO;
import cn.rkyang.community.dto.QuestionDTO;
import cn.rkyang.community.mapper.QuestionMapper;
import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.Question;
import cn.rkyang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题业务层
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/5 16:39
 */
@Service
public class QuestionService {

    private final QuestionMapper questionMapper;

    private final UserMapper userMapper;

    public QuestionService(QuestionMapper questionMapper, UserMapper userMapper) {
        this.questionMapper = questionMapper;
        this.userMapper = userMapper;
    }

    /**
     * 获取问题列表
     * @return
     * @param page
     * @param size
     */
    public PageDTO list(Integer page, Integer size) {
        List<Question> list = questionMapper.list((size * (page -1)), size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOS);
        pageDTO.setPageInfo(questionMapper.count(), page, size);
        return pageDTO;
    }

    public PageDTO findByUser(Integer id, Integer page, Integer size) {
        List<Question> list = questionMapper.findByUser(id, (size * (page -1)), size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOS);
        pageDTO.setPageInfo(questionMapper.countByUser(id), page, size);
        return pageDTO;
    }
}
