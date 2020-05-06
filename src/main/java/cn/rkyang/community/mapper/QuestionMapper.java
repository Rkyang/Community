package cn.rkyang.community.mapper;

import cn.rkyang.community.dto.QuestionDTO;
import cn.rkyang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 问题数据层
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/4 23:59
 */
@Mapper
public interface QuestionMapper {

    /**
     * 增加问题
     * @param question 问题模型
     */
    @Insert("insert into question (title,description,create_time,modified_time,creator,comment_count,view_count," +
            "like_count,tag) values (#{title},#{description},#{createTime},#{modifiedTime},#{creator}," +
            "#{commentCount},#{viewCount},#{likeCount},#{tag})")
    void create(Question question);

    /**
     * 问题列表
     * @return 查询结果
     * @param page 页条数
     * @param size 当前页
     */
    @Select("select * from question limit #{page}, #{size}")
    List<QuestionDTO> list(@Param("page") Integer page,  @Param("size") Integer size);

    /**
     * 数据总条数
     * @return 总条数
     */
    @Select("select count(1) from question")
    Integer count();
}
