package com.liveus.service.serviceImpl;

import com.liveus.dao.BlogtypeMapper;
import com.liveus.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("blogTypeService")
public class BlogTypeServiceImp implements BlogTypeService {

    @Autowired
    BlogtypeMapper blogtypeMapper;

    @Override
    public int addNewType(String type) {
        int result = blogtypeMapper.insertNewBlog(type);
        return result;
    }
}
