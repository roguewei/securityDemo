package com.winston.vaildate.code;

import com.winston.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName ValidateCodeBeanConfig
 * @Description
 * @Author Winston
 * @Date 2019/4/16 21:30
 * @Version 1.0
 **/
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    // @ConditionalOnMissingBean注解
    // 作用：在系统启动初始化ValidateCodeGenerator这个bean的时候会在spring容器中找
    // 是否存在名字为imageCodeGenerator的bean，如果存在则不会用方法体里面的了
    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

}
