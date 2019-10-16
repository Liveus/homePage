package com.liveus.service.serviceImpl;

import com.liveus.dao.BlogMapper;
import com.liveus.dao.BlogClassMapper;
import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.vo.BlogtypeVo;
import com.liveus.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

    @Resource
    BlogMapper blogMapper;

    @Autowired
    BlogClassMapper blogClassMapper;

    @Override
    public int newBlog(Blog blog) {
        int bo = blogMapper.insertBlog(blog);
        //如果新增了分类则在数据库中添加
        List<BlogtypeVo> list = this.blogClassMapper.getAllType();
        String[] blogclasses = blog.getBlogclass().split(",");
        for(String blogclass: blogclasses){
            if(!list.contains(new BlogtypeVo(blogclass))){
                this.blogClassMapper.insertNewBlog(blogclass,1);
            }
        }
        return bo;
    }

    @Override
    public List<Blog> getBlogs(String searchContent) {
        return blogMapper.getAllBlogs(searchContent);
    }

    @Override
    public List<String> getTitles(String searchTitle) {
        return blogMapper.getTitle(searchTitle);
    }

    @Override
    public Blog getBlogById(int id) {
        return blogMapper.getBlogById(id);
    }
}
