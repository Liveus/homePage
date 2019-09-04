package com.liveus.service.serviceImpl;

import com.liveus.dao.BlogMapper;
import com.liveus.domain.Blog;
import com.liveus.domain.User;
import com.liveus.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    BlogMapper blogMapper;

    @Override
    public int newBlog(Blog blog) {
        int bo = blogMapper.insertBlog(blog);
        return bo;
    }

    @Override
    public List<Blog> getBlogs(User user) {
        return blogMapper.getAllBlogs();
    }

    @Override
    public Blog getBlogById(int id) {
        return blogMapper.getBlogById(id);
    }
}
