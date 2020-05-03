package cn.rkyang.community.dto;

/**
 * AccessToken对象
 * @author Rkyang
 */
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

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", code='" + code + '\'' +
                ", redirect_uri='" + redirect_uri + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
