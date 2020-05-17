package cn.rkyang.community.service;

import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.User;
import org.springframework.stereotype.Service;

/**
 * 用户业务层
 * @author Rkyang
 * @version 1.0
 * @date 2020/5/17 13:34
 */
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 增加或更新
     * @param user 用户对象
     */
    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser == null) {
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
            userMapper.insert(user);
        }else {
            dbUser.setModifiedTime(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.update(dbUser);
        }
    }
}
