package com.winston.security.browser.support;

import lombok.Data;

/**
 * @ClassName SimpleResponse
 * @Description 简单的响应信息
 * @Author Winston
 * @Date 2019/4/14 23:11
 * @Version 1.0
 **/
@Data
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content){
        this.content = content;
    }

}
