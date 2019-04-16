package com.winston.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.winston.dao.UserDao2;
import com.winston.mydemo.domain.User;
import com.winston.mydemo.domain.UserQueryCondition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author Winston
 * @Date 2019/4/14 13:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao2 userDao;

    // 获取当前用户信息1
    @GetMapping("/me")
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // 获取当前用户信息2
    @GetMapping("/me2")
    public Object getCurrentUser2(Authentication authentication){
        // Authentication会自动到SecurityContextHolder中去找
        return authentication;
    }

    // 获取当前用户信息3，当不需要所以信息只需要用户信息时
    @GetMapping("/me3")
    public Object getCurrentUser3(@AuthenticationPrincipal UserDetails userDetails){
        // Authentication会自动到SecurityContextHolder中去找
        return userDetails;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询信息")
    public List<User> query(UserQueryCondition condition){
        return userDao.queryAll();
    }

    // :\\d+表示只能是数字
    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDatailView.class)
    public User queryParam(@ApiParam(value = "用户ID") @PathVariable String id){
//        throw new UserNotExistException(id);
        System.out.println("调用queryParam服务");
        return userDao.queryById(id);
    }

    // Valid字段上加上@NotBlank，实现非空校验
    // BindingResult在Valid校验失败时仍然能进入方法体进行逻辑处理
    @PostMapping
    public User createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(error ->
                    System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        return user;
    }

    // Valid字段上加上@NotBlank，实现非空校验
    // BindingResult在Valid校验失败时仍然能进入方法体进行逻辑处理
    @PutMapping("/{id:\\d+}")
    public User udpateUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(error ->
                    System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setUsername("wgs");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public String deleteUser(@PathVariable String id){
        return id;
    }

}
