package cn.shoppingguide.dao;

import cn.shoppingguide.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
//链接数据库底层
@Mapper
@Repository
public interface UserDao {

    User findByUsernameAndPassword(@Param("username") String username,
                                   @Param("password") String password);//登录

    User isexist(@Param("username") String username);//判断用户名是否已存在

    boolean updatePassword(@Param("username") String username,
                           @Param("password") String password);//修改密码

    boolean deleteUser(@Param("username") String username);//注销账号

    boolean insertUser(@Param("username") String username,
                    @Param("password") String password);//注册账号
}
