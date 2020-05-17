package cn.rkyang.community.mapper;

import cn.rkyang.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * 用户数据层
 * @author Rkyang
 * @date 2020/5/3 14:49
 * @version 1.0
 */
@Mapper
@Component(value = "UserMapper")
public interface UserMapper {

    /**
     * 增加用户
     * @param user 用户模型
     */
    @Insert("insert into user (name,account_id,token,create_time,modified_time,avatar_url) values (#{name}," +
            "#{accountId},#{token},#{createTime},#{modifiedTime},#{avatarUrl})")
    void insert(User user);

    /**
     * 通过token查询
     * @param token token
     * @return 用户模型
     */
    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    /**
     * 通过id查询
     * @param creator id
     * @return 用户模型
     */
    @Select("select * from user where id = #{creator}")
    User findById(Integer creator);

    /**
     * 通过AccountId查询
     * @param accountId accountId
     * @return 查询结果
     */
    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(String accountId);

    /**
     * 更新用户
     * @param dbUser 用户对象
     */
    @Update("update user set name=#{name},avatar_url=#{avatarUrl},token=#{token},modified_time=#{modifiedTime} where " +
            "id=#{id}")
    void update(User dbUser);
}
