package com.liveus.dao;

import com.liveus.domain.LearnResource;
import com.liveus.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LearnMapper extends MyMapper<LearnResource> {
    int add(LearnResource learnResource);
    int update(LearnResource learnResource);
    int deleteByIds(String[] ids);
    LearnResource queryLearnResouceById(Long id);
    public List<LearnResource> queryLearnResouceList(Map<String, Object> params);
}