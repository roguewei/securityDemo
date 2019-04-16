package com.winston.web.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName TimeFilter
 * @Description
 * @Author Winston
 * @Date 2019/4/14 16:37
 * @Version 1.0
 **/
// 没有@Component注解的话就需要在配置类中手动注入，本例在WebConfig中
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TIME FILTER INIT");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TIME FILTER DOFILTER start");
        chain.doFilter(request,response);
        System.out.println("TIME FILTER DOFILTER end");
    }

    @Override
    public void destroy() {
        System.out.println("TIME FILTER DESTROY");
    }
}
