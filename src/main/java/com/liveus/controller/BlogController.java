package com.liveus.controller;

import com.liveus.domain.Blog;
import com.liveus.domain.User;
import com.liveus.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/blog")
@Controller
public class BlogController {

    @Resource
    BlogService blogService;

    @RequestMapping("/newBlog")
    public String newBlog(){
        return "newBlog";
    }

    /**
     * 上传新blog
     * @param blog
     */
    @RequestMapping("/submit")
    @ResponseBody
    public void  submit(Blog blog){
        blog.setSummary("Test");
        blogService.newBlog(blog);
    }

    /**
     * 获取当前用户所有blog
     * @return
     */
    @RequestMapping("/allBlogs")
    @ResponseBody
    public List<Blog> getAllBlog(HttpServletRequest request){
        List<Blog> blogs = blogService.getBlogs((User) request.getSession().getAttribute("user"));
        return blogs;
    }

    /**
     * 获取指定id的blog
     * @param request
     * @return
     */

    @RequestMapping("/getBlogById")
    @ResponseBody
    public Blog getBlogById(HttpServletRequest request){
        return  blogService.getBlogById(1);
    }
}
