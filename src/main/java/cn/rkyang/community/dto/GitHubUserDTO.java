package cn.rkyang.community.dto;

import lombok.Data;

/**
 * GitHub用户数据传输对象
 * @author Rkyang
 */
@Data
public class GitHubUserDTO {

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户bio
     */
    private String bio;

    /**
     * 头像路径
     */
    private String avatarUrl;
}
