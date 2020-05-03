package cn.rkyang.community.controller;

import cn.rkyang.community.dto.AccessTokenDTO;
import cn.rkyang.community.dto.GitHubUserDTO;
import cn.rkyang.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rkyang
 */
@Controller
public class AuthorizeController {

    private final GitHubProvider gitHubProvider;

    public AuthorizeController(GitHubProvider gitHubProvider) {
        this.gitHubProvider = gitHubProvider;
    }

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    /**
     * GitHub授权登录callback
     * @param code GitHub返回的用户标识令牌
     * @param state 一个不可猜测的随机字符串，它用于防止跨站点请求伪造攻击
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state")String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUserDTO userDTO = gitHubProvider.getGitHubUser(accessToken);
        if (userDTO != null) {
            request.getSession().setAttribute("user",userDTO);
            return "redirect:/";
        }
        return "redirect:/";
    }
}
