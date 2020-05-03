package cn.rkyang.community.controller;

import cn.rkyang.community.dto.AccessTokenDTO;
import cn.rkyang.community.dto.GitHubUserDTO;
import cn.rkyang.community.provider.GitHubProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Rkyang
 */
@Controller
public class AuthorizeController {

    private final GitHubProvider gitHubProvider;

    public AuthorizeController(GitHubProvider gitHubProvider) {
        this.gitHubProvider = gitHubProvider;
    }

    /**
     * GitHub授权登录callback
     * @param code GitHub返回的用户标识令牌
     * @param state 一个不可猜测的随机字符串，它用于防止跨站点请求伪造攻击
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state")String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setClient_id("ec34caaee69b28f6a5a5");
        accessTokenDTO.setClient_secret("5b481b82eafd8a73499f06aa7643748d3d215e9b");
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUserDTO userDTO = gitHubProvider.getGitHubUser(accessToken);
        System.out.println(userDTO.getId());
        return "index";
    }
}
