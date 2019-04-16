package com.winston.config;

import com.winston.intercepror.TimeInterceptor;
import com.winston.web.filter.TimeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WebConfig
 * @Description
 * @Author Winston
 * @Date 2019/4/14 17:21
 * @Version 1.0
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        configurer.registerCallableInterceptors()
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
    }

//    @Bean
    public FilterRegistrationBean timeFilter(){
        // 为项目添加没有注入到spring中的过滤器
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        // 设置哪些路径需要被过滤器拦截
        List<String> urls = new ArrayList<>();
        // 表示所有路径都被过滤器拦截
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return  registrationBean;
    }

}
