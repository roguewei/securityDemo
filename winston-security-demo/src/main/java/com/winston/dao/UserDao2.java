package com.winston.dao;

import com.winston.mydemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao2 {

    @Select("select * from User")
    public List<User> queryAll();

    @Select("select * from User where id=#{id}")
    public User queryById(String id);

}
