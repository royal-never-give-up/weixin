package cn.ninetailfox.weixin.mapper;

import cn.ninetailfox.weixin.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user values(#{id}, #{username}, #{password})")
    @SelectKey(keyProperty = "id",
            resultType = String.class,
            before = true,
            statement = "select replace(uuid(),'-','') as value")
    @Options(keyProperty = "id", useGeneratedKeys = true)
    public int insertUser(User user);

    @Delete("delete from user where id = #{id}")
    public int deleteUser(String id);

    @Select("select * from user where id = #{id}")
    public User getUserById(String id);

    @Update("update user set username = #{username}, password = #{password} where id = #{id}")
    public int updateUser(User user);

    @Select("select * from user")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "password", property = "password")
    })
    public List<User> findAllUsers();
}
