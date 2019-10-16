package com.liveus.service.serviceImpl;

import com.liveus.dao.BlogClassMapper;
import com.liveus.pojo.vo.BlogtypeVo;
import com.liveus.service.BlogClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogTypeService")
public class BlogClassServiceImp implements BlogClassService {

    @Autowired
    BlogClassMapper blogClassMapper;

    @Override
    public int addNewType(String type) {
        int result = blogClassMapper.insertNewBlog(type,0);
        return result;
    }

    @Override
    public List<BlogtypeVo> getAllType() {
        return this.blogClassMapper.getAllType();
    }
}
