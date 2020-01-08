package com.liveus.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/12/18 11:21
 * @Copyright: © Liveus
 * @Warning: for fun
 */
//@Configuration
public class RedisTimerConfiguration {

    private Logger logger = LoggerFactory.getLogger(RedisTimerConfiguration.class);
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }

}
