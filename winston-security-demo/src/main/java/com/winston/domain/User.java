package com.winston.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.winston.validator.MyConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @ClassName domain
 * @Description
 * @Author Winston
 * @Date 2019/4/14 13:17
 * @Version 1.0
 **/
public class User {
    private Integer id;
    @MyConstraint(message = "这是一个测试注解")
    private String username;
    // password非空校验
    @NotBlank(message = "密码不能为空")
    private String password;
    // @Past校验时间不能超过当前时间
    @Past(message = "必须是过去的时间")
    private Date birthday;

    // 使用接口来声明多个视图
    public interface UserSimpleView{};
    public interface UserDatailView extends UserSimpleView {};

    // 再controller中使用@JsonView注解声明使用UserSimpleView时只显示查询出来的username
    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 再controller中使用@JsonView注解声明使用UserDatailView时,由于UserDatailView继承了
    // UserSimpleView,所以会显示查询出来的username和password
    @JsonView(UserDatailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
