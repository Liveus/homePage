package com.liveus.serviceImpl;

import com.liveus.domain.LearnResource;
import com.liveus.service.LearnService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LearnServiceImplTest {
    @Autowired
    private LearnService learnService;

    @Transactional//单元测试回滚
    @Test
    public void getLearn(){
        LearnResource learnResource=learnService.queryLearnResouceById(1001L);
        Assert.assertThat(learnResource.getAuthor(),is("嘟嘟MD独立博客"));
    }

    @Test
    @Transactional
    public void add(){
        LearnResource bean = new LearnResource();
        bean.setAuthor("测试回滚");
        bean.setTitle("回滚用例");
        bean.setUrl("http://tengj.top");
        learnService.add(bean);
    }

}