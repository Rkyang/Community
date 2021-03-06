package cn.rkyang.community.controller;

import cn.rkyang.community.dto.AccessTokenDTO;
import cn.rkyang.community.dto.GitHubUserDTO;
import cn.rkyang.community.model.User;
import cn.rkyang.community.provider.GitHubProvider;
import cn.rkyang.community.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登录控制层
 * @author Rkyang
 */
@Controller
public class AuthorizeController {

    private final GitHubProvider gitHubProvider;

    private final UserService userService;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    public AuthorizeController(GitHubProvider gitHubProvider, UserService userService) {
        this.gitHubProvider = gitHubProvider;
        this.userService = userService;
    }

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
            //此处由于自己的Github账号没有设置Name，所以先写死
            user.setName(/*userDTO.getName()*/"Rkyang");
            user.setAccountId(String.valueOf(userDTO.getId()));
            user.setAvatarUrl(userDTO.getAvatarUrl());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
        }
        return "redirect:/";
    }

    /**
     * 退出登录
     * @return 主页面
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
