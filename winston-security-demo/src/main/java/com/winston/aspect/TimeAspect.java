package com.winston.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @ClassName TimeAspect
 * @Description Aspect切片类
 * 需要实现切入点，增强方法
 * @Author Winston
 * @Date 2019/4/14 18:33
 * @Version 1.0
 **/
//@Aspect
//@Component
public class TimeAspect {

    // 增强方法
    @Around("execution(* com.winston.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");

        //获取请求的参数
        Object[] args = pjp.getArgs();
        for (Object arg : args){
            System.out.println("arg is "+arg);
        }
        // 调用被拦截的控制器中当前调用的方法
        Object object = pjp.proceed();

        System.out.println("time aspect end");
        return object;
    }

}
