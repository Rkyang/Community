package cn.rkyang.community.service;

import cn.rkyang.community.dto.PageDTO;
import cn.rkyang.community.dto.QuestionDTO;
import cn.rkyang.community.mapper.QuestionMapper;
import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.Question;
import cn.rkyang.community.model.QuestionExample;
import cn.rkyang.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
     * 问题列表
     * @param page 当前页
     * @param size 页条数
     * @return pageDTO
     */
    public PageDTO list(Integer page, Integer size) {
        List<Question> list = questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),
         new RowBounds(size * (page - 1), size));
        List<QuestionDTO> dtoList = setDTO(list);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(dtoList);
        pageDTO.setPageInfo((int) questionMapper.countByExample(new QuestionExample()), page, size);
        return pageDTO;
    }

    /**
     * 我的问题
     * @param id 用户id
     * @param page 当前页
     * @param size 页条数
     * @return pageDTO
     */
    public PageDTO findByUser(Integer id, Integer page, Integer size) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(id);
        List<Question> list = questionMapper.selectByExampleWithBLOBsWithRowbounds(questionExample,
                new RowBounds(size * (page -1), size));
        List<QuestionDTO> dtoList = setDTO(list);
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(dtoList);
        pageDTO.setPageInfo((int) questionMapper.countByExample(questionExample), page, size);
        return pageDTO;
    }

    /**
     * 通用封装：对象转DTO
     * @param questions 对象
     * @return DTO集合
     */
    private List<QuestionDTO> setDTO(List<Question> questions) {
        List<QuestionDTO> dtoList = new ArrayList<QuestionDTO>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator().toString());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            dtoList.add(questionDTO);
        }
        return dtoList;
    }

    /**
     * 通过id查询
     * @param id id
     * @return 查询结果
     */
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator().toString());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 问题增加或修改
     * @param question 问题对象
     */
    public void createOrUpdate(Question question) {
        if (StringUtils.isEmpty(question.getId())) {
            question.setCreateTime(System.currentTimeMillis());
            question.setModifiedTime(question.getCreateTime());
            questionMapper.insert(question);
        }else {
            question.setModifiedTime(System.currentTimeMillis());
            questionMapper.updateByPrimaryKeySelective(question);
        }
    }
}
