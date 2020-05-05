package cn.rkyang.community.model;

import lombok.Data;

/**
 * 问题模型
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/5 00:02
 */
@Data
public class Question {

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
    private Integer commentCount = 0;

    /**
     * 浏览数
     */
    private Integer viewCount = 0;

    /**
     * 点赞数
     */
    private Integer likeCount = 0;

    /**
     * 标签
     */
    private String tag;
}
