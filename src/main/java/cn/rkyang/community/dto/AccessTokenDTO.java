package cn.rkyang.community.dto;

import lombok.Data;

/**
 * AccessToken数据传输对象
 * @author Rkyang
 */
@Data
public class AccessTokenDTO {

    /**
     * 必需，从 GitHub 收到的 GitHub 应用的客户端 ID
     */
    private String client_id;

    /**
     * 必需，从 GitHub 收到的 GitHub 应用的客户端密钥
     */
    private String client_secret;

    /**
     * 必需，请求用户GitHub标识时返回的交换令牌
     */
    private String code;

    /**
     * 应用程序中在授权后发送用户的 URL
     */
    private String redirect_uri;

    /**
     * 一个不可猜测的随机字符串，它用于防止跨站点请求伪造攻击
     */
    private String state;
}
