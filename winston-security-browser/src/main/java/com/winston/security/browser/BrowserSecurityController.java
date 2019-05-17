package com.winston.security.browser;

import com.winston.mydemo.dao.UserDao;
import com.winston.mydemo.domain.User;
import com.winston.result.CodeMsg;
import com.winston.result.Result;
import com.winston.security.core.properties.LoginType;
import com.winston.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName BrowserSecurityController
 * @Description
 * @Author Winston
 * @Date 2019/4/14 23:01
 * @Version 1.0
 **/
@RestController
@RequestMapping("/authentication")
public class BrowserSecurityController {

    @Autowired
    private UserDao userDao;

    // 判断引发登录跳转的是否是HTML引发的
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 重定向 策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

     /*
     * @Author Winston
     * @Description //当需要身份认证时跳转到这里
     * @Date 23:01 2019/4/14
     * @Param
     * @return
     **/
     @RequestMapping(value = "/require")
     @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Result requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
         // 获取引发跳转的请求

         SavedRequest savedRequest = requestCache.getRequest(request, response);
         if(savedRequest != null){
             // 获取URL
             String target = savedRequest.getRedirectUrl();
             System.out.println("引发跳转的请求是：" + target);
             if(StringUtils.endsWithIgnoreCase(target, ".html")){
                 // 如果是HTML的请求，转发到第三个参数配置的URL
                 redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
             }
         }

         return Result.error(CodeMsg.REQUEST_ILLEGAL);
    }

    @RequestMapping("/form")
    public Result queryForm(){
         return Result.success(userDao.queryAll());
    }

}
