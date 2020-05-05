package cn.rkyang.community.dto;

import cn.rkyang.community.model.User;
import lombok.Data;

/**
 * 问题数据传输对象
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/5 16:37
 */
@Data
public class QuestionDTO {

    /**
     * 问题id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long modifiedTime;

    /**
     * 创建用户
     */
    private Integer creator;

    /**
     * 评论数
     */
    private Integer commentCount;

    /**
     * 浏览数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 标签
     */
    private String tag;

    /**
     * 用户模型
     */
    private User user;
}
