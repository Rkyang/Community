package cn.rkyang.community.service;

import cn.rkyang.community.dto.PageDTO;
import cn.rkyang.community.dto.QuestionDTO;
import cn.rkyang.community.mapper.QuestionMapper;
import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.User;
import org.springframework.stereotype.Service;

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
        List<QuestionDTO> list = questionMapper.list((size * (page -1)), size);
        for (QuestionDTO questionDTO : list) {
            User user = userMapper.findById(questionDTO.getCreator());
            questionDTO.setUser(user);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(list);
        pageDTO.setPageInfo(questionMapper.count(), page, size);
        return pageDTO;
    }
}
