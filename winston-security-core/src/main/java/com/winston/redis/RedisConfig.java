package com.winston.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author weigaosheng
 * @description
 * @CalssName RedisConfig
 * @date 2019/3/1
 * @params
 * @return
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfig {
    private String host;
    private int port;
    private int timeout;
    private String password;

    @Bean
    public JedisPool JedisPoolFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        return new JedisPool(poolConfig, this.host, this.port, this.timeout * 1000, this.password,0);
    }
}
