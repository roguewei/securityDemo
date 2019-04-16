package com.winston.security.browser.authentication;

import com.alibaba.fastjson.JSON;
import com.winston.result.Result;
import com.winston.security.core.properties.LoginType;
import com.winston.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ImoocAuthenticationFailHandler
 * @Description 登录失败处理器
 * @Author Winston
 * @Date 2019/4/15 20:12
 * @Version 1.0
 **/
@Component
@Slf4j
// 方法一：实现spring security的失败处理接口,不能分别出网页HTML请求还是APP请求
//public class ImoocAuthenticationFailHandler implements AuthenticationFailureHandler {
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        log.info("登录失败");
//
//        httpServletResponse.setStatus(500);
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.getWriter().write(JSON.toJSONString(e));
//    }
//}
// 方法二：继承spring默认的失败处理类,可以分别出网页HTML请求还是APP请求
public class ImoocAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败");
        log.info("LoginType.JSON---"+LoginType.JSON);
        log.info("securityProperties.getBrowser().getLoginType())---"+securityProperties.getBrowser().getLoginType());
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            httpServletResponse.setStatus(500);
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(Result.success(e.getMessage())));
        }else{
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }

    }
}
