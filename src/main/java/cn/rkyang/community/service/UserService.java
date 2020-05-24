package cn.rkyang.community.service;

import cn.rkyang.community.mapper.UserMapper;
import cn.rkyang.community.model.User;
import cn.rkyang.community.model.UserExample;
import org.springframework.stereotype.Service;

import java.util.List;

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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users == null && users.size() == 0) {
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
            userMapper.insertSelective(user);
        }else {
            User dbUser = users.get(0);
            dbUser.setModifiedTime(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.updateByExampleSelective(dbUser, userExample);
        }
    }
}
