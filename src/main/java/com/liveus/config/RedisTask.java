package com.liveus.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/12/18 11:24
 * @Copyright: © Liveus
 * @Warning: for fun
 */
//@Component
public class RedisTask extends KeyExpirationEventMessageListener {

    private Logger logger = LoggerFactory.getLogger(RedisTask.class);

    public RedisTask(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        logger.info("redis key 过期：pattern={},channel={},key={}", new String(pattern), channel, key);
    }
}
