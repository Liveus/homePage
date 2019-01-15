package com.liveus.service;

import com.liveus.domain.LearnResource;

public interface LearnService {
    int add(LearnResource learnResource);
    int update(LearnResource learnResource);
    int deleteByIds(String[] ids);
    LearnResource queryLearnResouceById(Long learnResouce);
}