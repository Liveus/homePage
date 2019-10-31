package com.liveus.service;

import com.liveus.pojo.dto.BlogDto;
import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.entity.User;

import java.util.List;

public interface BlogService {
    /**
     * add new blog
     * @param blog
     * @return result?1:0
     */
    int newBlog(Blog blog,List<String> newBlogClass);

    /**
     * get all blogs
     * @param blogDto
     * @return
     */
    List<Blog> getBlogs(BlogDto blogDto);

    /**
    * @Desc:  查询文章标题
    * @author: shenliqiang
    * @Time: 2019/10/14 11:29
    * @param searchTitle
    * @return
    */

    List<String> getTitles(String searchTitle);
    /**
     * 根据blog id获取blog详情
     * @param id
     * @return
     */
    Blog getBlogById(int id);
}
