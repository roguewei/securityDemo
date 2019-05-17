package com.winston.intercepror;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @ClassName TimeInterceptor
 * @Description 拦截器
 * @Author Winston
 * @Date 2019/4/14 17:27
 * @Version 1.0
 **/
// 跟filter不同，光在本类使用注解@Component是不能起作用的
// 需要在配置类、即有@Configuration注解并且继承了WebMvcConfigurerAdapter的类中添加配置，
// 本例在WebConfig中
@Component
public class TimeInterceptor implements HandlerInterceptor {
    /**
        * @Author Winston
        * @Description 真正调用controller方法之前调用该拦截方法
        * @Date 2019/5/12:18:28
        * @Param
        * @Retuen
    **/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.out.println("preHandle");

        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod)handler).getMethod().getName());
        // 为了传递信息给拦截器的postHandle方法，只能通过设置请求的attribute来进行传递
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        return true;
    }

    /**
        * @Author Winston
        * @Description controller方法执行之后调用该方法，
        * 但是controller方法抛出异常则不会调用该方法
        * @Date 2019/5/12:18:29
        * @Param
        * @Retuen
    **/
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+ (new Date().getTime()-start));
    }

    /**
        * @Author Winston
        * @Description controller方法执行完，不管是否有异常抛出都会执行该方法
        * @Date 2019/5/12:18:30
        * @Param
        * @Retuen
    **/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        Long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor 耗时："+ (new Date().getTime()-start));
        System.out.println("ex is "+e);
    }
}
