package com.winston.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName HelloService
 * @Description
 * @Author Winston
 * @Date 2019/4/14 15:53
 * @Version 1.0
 **/
@Service
public class HelloService {

    public String greeting(String name){
        System.out.println("greeting --- "+name);
        return "hello :"+name;
    }

}
