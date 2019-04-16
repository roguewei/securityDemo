package com.winston.security.core;

import com.winston.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SecurityCoreConfig
 * @Description
 * @Author Winston
 * @Date 2019/4/14 23:25
 * @Version 1.0
 **/
@Configuration
// 让读取配置文件的SecurityProperties类生效
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
