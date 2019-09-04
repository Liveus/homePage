package com.liveus.dao;

import com.liveus.domain.Blogtype;
import com.liveus.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface BlogtypeMapper extends MyMapper<Blogtype> {
    int insertNewBlog(String type);
}