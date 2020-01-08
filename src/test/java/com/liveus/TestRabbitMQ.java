package com.liveus;

import com.liveus.core.blog.pojo.entity.Blog;
import com.liveus.config.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

    @Autowired
    private HelloSender helloSender;

    /**
     * string传输
     */
    //@Test
    public void testRabbit() {
        for(int i=0;i<50;i++){
            helloSender.send("hellomsg:"+i);
        }
    }

    /**
     * 实体类传输
     */
    //@Test
    public void testObj() {
        for(int i=0;i<10;i++){
            Blog blog = new Blog();
            blog.setId(i);
            helloSender.sendBlog(blog);
        }
    }

    /**
     * g广播测试
     */
    //@Test
    public void Testbroader(){
        helloSender.send();
    }
}