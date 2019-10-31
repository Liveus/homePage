package com.liveus.dao;

import com.liveus.pojo.dto.BlogDto;
import com.liveus.pojo.entity.Blog;
import com.liveus.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper extends MyMapper<Blog> {
    int insertBlog(Blog blog);

    Blog getBlogById(int id);

    // 模糊查询所有blog
    List<Blog> getAllBlogs(BlogDto dto);

    // 模糊查询所有标题
    List<String> getTitle(@Param("searchTitle") String searchTitle);
}