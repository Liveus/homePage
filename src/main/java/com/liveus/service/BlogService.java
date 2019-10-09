package com.liveus.service;

import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.entity.User;

import java.util.List;

public interface BlogService {
    /**
     * add new blog
     * @param blog
     * @return result?1:0
     */
    int newBlog(Blog blog);

    /**
     * get all blogs
     * @param user
     * @return
     */
    List<Blog> getBlogs(User user);

    /**
     * 根据blog id获取blog详情
     * @param id
     * @return
     */
    Blog getBlogById(int id);
}
