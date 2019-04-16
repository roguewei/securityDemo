package com.winston.mydemo.dao;

import com.winston.mydemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from User")
    public List<User> queryAll();

    @Select("select * from User where id=#{id}")
    public User queryById(String id);

    @Select("select * from User where username=#{username}")
    public User queryByUserName(String username);

}
