package com.liveus.controller;

import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.entity.User;
import com.liveus.service.BlogService;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/blog")
@RestController
public class BlogController {

    @Resource
    BlogService blogService;

    /**
     * 上传新blog
     * @param blog
     */
    @PostMapping("/submit")
    @ResponseBody
    public void submit(Blog blog){
        //设置默认为1
        blog.setCreate_by(1);
        blogService.newBlog(blog);
    }

    /**
     * 获取当前用户所有blog
     * @return
     */
    @RequestMapping(value = "/allBlogs",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public List<Blog> getAllBlog(@RequestParam("searchContent") String searchContent){
        return blogService.getBlogs(searchContent);
    }

    /**
     * 获取当前用户所有blog
     * @return
     */
    @RequestMapping(value = "/searchTitle",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public List<String> getTitles(@RequestParam("searchTitle") String searchTile){
        return blogService.getTitles(searchTile);
    }

    /**
     * 获取指定id的blog
     * @param blogId
     * @return
     */

    @RequestMapping("/getBlogById")
    @ResponseBody
    @CrossOrigin
    public Blog getBlogById(@RequestParam("blogId") Integer blogId){
        return  blogService.getBlogById(blogId);
    }


}