package com.liveus.controller;

import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.entity.User;
import com.liveus.service.BlogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/blog")
@RestController
public class BlogController {

    @Resource
    BlogService blogService;

    @Autowired
    private HttpServletRequest request;

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
    @PostMapping(value = "/allBlogs")
    @ResponseBody
    @CrossOrigin
    public List<Blog> getAllBlog(@RequestParam("searchContent") String searchContent){
        return blogService.getBlogs(searchContent);
    }

    /**
     * 获取当前用户所有blog
     * @return
     */
    @PostMapping(value = "/searchTitle")
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

    @GetMapping("/getBlogById/{blogId}")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "获取blog详细内容",httpMethod = "GET",notes = "获取blog详细内容")
    @ApiParam(name = "blogId",value = "Integer",required = true)
    public Blog getBlogById(@PathVariable("blogId") Integer blogId){
        System.out.println("zz");
        return  blogService.getBlogById(blogId);
    }


    @PostMapping(value="/uploadSource")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "blog资源上传",httpMethod = "POST",notes = "blog资源上传")
    @ApiParam(name = "newType",value = "新类型",required = true)
    public String upload(MultipartFile file) {
        // 判断文件是否为空
        String filePath = "";
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                filePath = request.getSession().getServletContext().getRealPath("/") /*+ "upload/"*/
                        + file.getOriginalFilename();
                System.out.println(filePath);
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    /***
     * 读取上传文件中得所有文件并返回
     *
     * @return
     */
    @RequestMapping("list")
    public ModelAndView list() {
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/";
        ModelAndView mav = new ModelAndView("list");
        File uploadDest = new File(filePath);
        String[] fileNames = uploadDest.list();
        for (int i = 0; i < fileNames.length; i++) {
            //打印出文件名
            System.out.println(fileNames[i]);
        }
        return mav;
    }
}