package cn.rkyang.community.mapper;

import cn.rkyang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 问题数据层
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/4 23:59
 */
@Mapper
@Component(value = "QuestionMapper")
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
     * @param page 当前页
     * @param size 页条数
     */
    @Select("select * from question limit #{page}, #{size}")
    List<Question> list(@Param("page") Integer page,  @Param("size") Integer size);

    /**
     * 数据总条数
     * @return 总条数
     */
    @Select("select count(1) from question")
    Integer count();

    /**
     * 通过用户查询
     * @param id 用户id
     * @param page 当前页
     * @param size 页条数
     * @return 问题模型
     */
    @Select("select * from question where creator = #{id} limit #{page}, #{size}")
    List<Question> findByUser(@Param("id") Integer id, @Param("page") Integer page, @Param("size") Integer size);

    /**
     * 通过用户查询总条数
     * @param id 用户id
     * @return 总条数
     */
    @Select("select count(1) from question where creator = #{id}")
    Integer countByUser(@Param("id") Integer id);
}
