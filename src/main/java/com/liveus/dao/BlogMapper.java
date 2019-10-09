package com.liveus.dao;

import com.liveus.pojo.entity.Blog;
import com.liveus.utils.MyMapper;

import java.util.List;

public interface BlogMapper extends MyMapper<Blog> {
    int insertBlog(Blog blog);

    Blog getBlogById(int id);

    List<Blog> getAllBlogs();
}