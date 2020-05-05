package cn.rkyang.community.mapper;

import cn.rkyang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户数据层
 * @author Rkyang
 * @date 2020/5/3 14:49
 * @version 1.0
 */
@Mapper
public interface UserMapper {

    /**
     * 增加用户
     * @param user 用户模型
     */
    @Insert("insert into user (name,account_id,token,create_time,modified_time) values (#{name},#{accountId}," +
            "#{token},#{createTime},#{modifiedTime})")
    void insert(User user);

    /**
     * 通过token查询
     * @param token token
     * @return 用户模型
     */
    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
