package com.liveus.core.blog.service.impl;

import com.liveus.core.blog.dao.BlogClassDao;
import com.liveus.core.blog.mapper.BlogClassMapper;
import com.liveus.core.user.pojo.vo.BlogtypeVo;
import com.liveus.core.blog.service.BlogClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogClassServiceImp implements BlogClassService {

    @Autowired
    BlogClassMapper blogClassMapper;

    @Autowired
    BlogClassDao blogClassDao;

    @Override
    public int addNewType(String type) {
        return blogClassMapper.insertNewBlog(type,0);
    }

    @Override
    public List<BlogtypeVo> getAllType() {
        return blogClassDao.getAllType();
    }
}
