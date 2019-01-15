package com.liveus.serviceImpl;


import com.liveus.dao.LearnMapper;
import com.liveus.domain.LearnResource;
import com.liveus.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by tengj on 2017/4/7.
 */
@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    LearnMapper learnMapper;
    @Override
    public int add(LearnResource learnResource) {
        return this.learnMapper.add(learnResource);
    }

    @Override
    public int update(LearnResource learnResource) {
        return this.learnMapper.update(learnResource);
    }

    @Override
    public int deleteByIds(String[] ids) {
        return this.learnMapper.deleteByIds(ids);
    }

    @Override
    public LearnResource queryLearnResouceById(Long id) {
        return this.learnMapper.queryLearnResouceById(id);
    }

}