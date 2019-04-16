package com.winston.security.browser.authentication;

import com.alibaba.fastjson.JSON;
import com.winston.security.core.properties.LoginType;
import com.winston.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ImoocAuthenticationSuccessHandler
 * @Description 登录成功后的处理行为
 * @Author Winston
 * @Date 2019/4/15 19:41
 * @Version 1.0
 **/
@Slf4j
@Component
// 方法一：实现spring security的登录成功处理器AuthenticationSuccessHandler,不能分别出网页HTML请求还是APP请求
//public class ImoocAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//
//    // 登录成功后会调用这个方法
//    // 参数authentication是封装认证信息，比如认证请求的IP，session，userdetail信息
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        log.info("登录成功");
//
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(JSON.toJSONString(authentication));
//    }
//}

// 方法二：继承spring默认的成功处理器,可以分别出网页HTML请求还是APP请求
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Autowired
    private SecurityProperties securityProperties;

    // 登录成功后会调用这个方法
    // 参数authentication是封装认证信息，比如认证请求的IP，session，userdetail信息
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        log.info("LoginType.JSON---"+LoginType.JSON);
        log.info("securityProperties.getBrowser().getLoginType())---"+securityProperties.getBrowser().getLoginType());
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(authentication));
        }else{
            // 跳转
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}
