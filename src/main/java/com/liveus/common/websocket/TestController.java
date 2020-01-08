package com.liveus.common.websocket;

import com.liveus.common.annotation.PassToken;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Desc: Test
 * @author: Lenovo
 * @Time: 2019/12/10 16:45
 * @Copyright: © Liveus
 * @Warning: for fun
 */
@RestController
public class TestController {

    private WebSocketHandler webSocketHandler = new WebSocketHandler();

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @GetMapping("/websocket/sendMessage")
    @PassToken
    public void sendMessage(){
        webSocketHandler.sendMessageToCompany("99999","message");
    }

    @GetMapping("/redis/setKey")
    @PassToken
    public void setKey(){
        redisTemplate.opsForValue().set("DASD","azdasd",10, TimeUnit.SECONDS);
        System.out.println("-------");
        List<User> users =  new ArrayList<>();
    }
}
