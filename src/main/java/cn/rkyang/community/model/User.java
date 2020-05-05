package cn.rkyang.community.model;

import lombok.Data;

/**
 * 用户模型，数据库用
 * @author Rkyang
 * @date 2020/5/3 14:52
 * @version 1.0
 */
@Data
public class User {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户name
     */
    private String name;

    /**
     * 用户accountId
     */
    private String accountId;

    /**
     * 用户token
     */
    private String token;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long modifiedTime;

    /**
     * 头像路径
     */
    private String avatarUrl;
}
