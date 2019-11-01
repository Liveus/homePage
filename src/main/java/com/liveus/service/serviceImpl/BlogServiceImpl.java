package com.liveus.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liveus.dao.BlogMapper;
import com.liveus.dao.BlogClassMapper;
import com.liveus.enums.RecommendType;
import com.liveus.pojo.dto.BlogDto;
import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.vo.BlogRecommendVo;
import com.liveus.pojo.vo.BlogVo;
import com.liveus.pojo.vo.BlogtypeVo;
import com.liveus.service.BlogService;
import com.liveus.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogMapper,Blog> implements BlogService {

    @Resource
    BlogMapper blogMapper;

    @Resource
    BlogClassMapper blogClassMapper;

    @Override
    public int newBlog(Blog blog,List<String> newBlogClass) {
        //如果新增了分类则在数据库中添加
        List<BlogtypeVo> list = this.blogClassMapper.getAllType();
        for(String blogclass: newBlogClass){
            if(!list.contains(new BlogtypeVo(blogclass))){
                this.blogClassMapper.insertNewBlog(blogclass,1);
                list.add(new BlogtypeVo(blogclass));
            }
            blog.setBlogclass(blog.getBlogclass()+","+this.blogClassMapper.selectByName(blogclass));
        }
        return blogMapper.insertBlog(blog);
    }

    @Override
    public List<Blog> getBlogs(BlogDto blogDto) {
        List<Blog> blogs = blogMapper.getAllBlogs(blogDto);
        for (Blog blog:blogs) {
            StringBuilder result = new StringBuilder("");
            String[] classes = blog.getBlogclass().split(",");
            for (String blogclass:classes) {
                result.append(this.blogClassMapper.selectById(Integer.valueOf(blogclass))).append(",");
            }
            result.deleteCharAt(result.length()-1);
            blog.setBlogclass(result.toString());
        }
        return blogs;
    }

    @Override
    public Page<BlogVo> queryByPageList(Page<BlogVo> page, BlogDto dto) {
        List<BlogVo> blogs = baseMapper.queryByPageList(page, ObjectUtils.objectToMap(dto));
        for (BlogVo blog:blogs) {
            StringBuilder result = new StringBuilder("");
            String[] classes = blog.getBlogclass().split(",");
            for (String blogclass:classes) {
                result.append(this.blogClassMapper.selectById(Integer.valueOf(blogclass))).append(",");
            }
            result.deleteCharAt(result.length()-1);
            blog.setBlogclass(result.toString());
        }
        return page.setRecords(blogs);
    }

    @Override
    public List<String> getTitles(String searchTitle) {
        return blogMapper.getTitle(searchTitle);
    }

    @Override
    public Blog getBlogById(int id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<BlogRecommendVo> getRecommendedBlog(Integer type, Integer id) {
        if(type.equals(RecommendType.BY_TIME.value())){
            return baseMapper.getRecommendedBlogByTime(id);
        }else if(type.equals(RecommendType.BY_CLASS.value())){
            return baseMapper.getRecommendedBlogByClass(id);
        }else if(type.equals(RecommendType.BY_TAG.value())){
            return baseMapper.getRecommendedBlogByTag(id);
        }else {
            return null;
        }
    }
}
