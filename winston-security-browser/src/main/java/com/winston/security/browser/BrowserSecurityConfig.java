package com.winston.security.browser;

import com.winston.security.browser.authentication.ImoocAuthenticationFailHandler;
import com.winston.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.winston.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName BrowserSecurityConfig
 * @Description web应用的安全适配器
 * @Author Winston
 * @Date 2019/4/14 20:52
 * @Version 1.0
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    // 设置登录成功后使用自己定义的处理器
    @Autowired
    private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    // 设置登录失败后使用自己定义的处理器
    @Autowired
    private ImoocAuthenticationFailHandler imoocAuthenticationFailHandler;

    /**
     * @Author Winston
     * @Description 添加了这个bean之后，userdetailsservice里校验前端输入的密码时就会自动把前端传过来的密码进行加密，
     * 然后再去跟数据库中的密码去比对
     * @Date 2019/5/12:22:03
     * @Param
     * @Retuen
    **/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        // 验证码错误等于认证失败，需要到认证失败的处理器类中执行
//        validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailHandler);
//
//        validateCodeFilter.setSecurityProperties(securityProperties);
//        validateCodeFilter.afterPropertiesSet();

        http
                // 将自己的过滤器添加到security的UsernamePasswordAuthenticationFilter过滤器前面执行
//                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 基本登录框
//                .httpBasic()
                // 用表单登录
                .formLogin()
                // 登录页面——浏览器网页的登录页面
//                .loginPage("/signIn.html")
                // 登录页面——前后端分离的登录提示
                .loginPage("/authentication/require")
                // 告诉security登录请求该URL
                .loginProcessingUrl("/authentication/form")
                // 使用自己定义的登录成功处理器
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailHandler)
                // 连接模块
                .and()
                // 对请求做授权
                .authorizeRequests()
                // 匹配url
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image",
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**")
                // 不需要权限
                .permitAll()
                // 任何请求
                .anyRequest()
                // 需要身份认证
                .authenticated()
                .and()
                .csrf()
                .disable();

    }
}
