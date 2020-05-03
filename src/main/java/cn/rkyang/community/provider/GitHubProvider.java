package cn.rkyang.community.provider;

import cn.rkyang.community.dto.AccessTokenDTO;
import cn.rkyang.community.dto.GitHubUserDTO;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * GitHub授权登录
 * @author Rkyang
 */
@Component
public class GitHubProvider {

    /**
     * 从GitHub获取AccessToken
     * @param accessTokenDTO GitHubAccessToken数据传输对象
     * @return null or token
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String tokenString = string.split("&")[0];
            String token = tokenString.split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从GitHub获取用户
     * @param accessToken accessToken
     * @return null or GitHub用户数据传输对象
     */
    public GitHubUserDTO getGitHubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {Response response =client.newCall(request).execute();
            String string = response.body().string();
            GitHubUserDTO gitHubUserDTO = JSON.parseObject(string, GitHubUserDTO.class);
            return gitHubUserDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
