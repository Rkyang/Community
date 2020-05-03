package cn.rkyang.community.controller;

import cn.rkyang.community.dto.AccessTokenDTO;
import cn.rkyang.community.dto.GitHubUserDTO;
import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.User;
import cn.rkyang.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登录控制
 * @author Rkyang
 */
@Controller
public class AuthorizeController {

    private final GitHubProvider gitHubProvider;

    public AuthorizeController(GitHubProvider gitHubProvider, UserMapper userMapper) {
        this.gitHubProvider = gitHubProvider;
        this.userMapper = userMapper;
    }

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    private final UserMapper userMapper;

    /**
     * GitHub授权登录callback
     * @param code GitHub返回的用户标识令牌
     * @param state 一个不可猜测的随机字符串，它用于防止跨站点请求伪造攻击
     * @return 重定向路径
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code, @RequestParam(name = "state")String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUserDTO userDTO = gitHubProvider.getGitHubUser(accessToken);
        if (userDTO != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(userDTO.getName());
            user.setAccountId(String.valueOf(userDTO.getId()));
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));
        }
        return "redirect:/";
    }
}
