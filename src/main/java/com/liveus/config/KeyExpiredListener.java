package com.liveus.config;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/12/18 13:31
 * @Copyright: © Liveus
 * @Warning: for fun
 */
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

public class KeyExpiredListener extends KeyExpirationEventMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyExpiredListener.class);

    public KeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        //过期的key
        String key = new String(message.getBody(), StandardCharsets.UTF_8);

        LOGGER.info("redis key 过期：pattern={},channel={},key={}", new String(pattern), channel, key);

    }
}