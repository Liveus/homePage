package com.liveus.service.serviceImpl;

import com.liveus.pojo.entity.Blog;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate template;

    public void send(String msg) {
        template.convertAndSend("queue",msg);
    }

    public void sendBlog(Blog blog) {
        template.convertAndSend("Blogqueue",blog);
    }

    public void send() {
        String msgString="fanoutSender :hello i am hzb";
        System.out.println(msgString);
        template.convertAndSend("fanoutExchange","abcd.ee", msgString);
    }
}
