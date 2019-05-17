package com.winston.vaildate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @ClassName ValidateCodeGenerator
 * @Description 校验码的生成器，供他人覆盖系统的配置
 * @Author Winston
 * @Date 2019/4/16 21:24
 * @Version 1.0
 **/
public interface ValidateCodeGenerator {

    public ImageCode createImageCode(ServletWebRequest request);
    
}
