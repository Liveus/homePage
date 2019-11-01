package com.liveus.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.liveus.pojo.dto.BlogDto;
import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.vo.BlogRecommendVo;
import com.liveus.pojo.vo.BlogVo;
import com.liveus.service.BlogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Calendar;
import java.util.List;

@RequestMapping("/blog")
@RestController
public class BlogController {

    @Resource
    BlogService blogService;

    @Resource
    private HttpServletRequest request;

    /**
     * 上传新blog
     * @param blog
     */
    @PostMapping("/submit")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "上传blog",httpMethod = "POST",notes = "上传blog")
    public void submit(Blog blog, @RequestParam("newBlogClass")List<String> newBlogClass){
        //设置默认为1
        var a = 1;
        blog.setCreate_by(a);
        blogService.newBlog(blog,newBlogClass);
    }

    /**暂无使用-------------------------------------
     * 获取当前用户所有blog
     * @return
     */
    @PostMapping(value = "/allBlogs")
    @ResponseBody
    @CrossOrigin
    public List<Blog> getAllBlog(BlogDto dto){
        return blogService.getBlogs(dto);
    }

    @GetMapping(value = "/getBlogs")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "分页查询",httpMethod = "GET",notes = "分页查询")
    public Page<BlogVo> queryByPageList(@ModelAttribute BlogDto dto){
        return this.blogService.queryByPageList(new Page<>(dto.getPage(),dto.getPageSize()),dto);
    }

    /**
     * 获取当前用户所有blog标题
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
        return  blogService.getBlogById(blogId);
    }

    /**获取推荐的blog
    * @Desc:
    * @author: shenliqiang
    * @Time: 2019/11/1 17:05
    * @param null
    * @return
    */

    @GetMapping("/getRecommend")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "获取推荐的blog",httpMethod = "GET",notes = "获取推荐的blog")
    public List<BlogRecommendVo> getRecommendBlog(@ModelAttribute("type") Integer type, @ModelAttribute("id") Integer id){
        return this.blogService.getRecommendedBlog(type,id);
    }

    /**
    * @Desc:  上传blog附件中的资源
    * @author: shenliqiang
    * @Time: 2019/10/31 15:19
    * @param
    * @return
    */

    @PostMapping(value="/uploadSource")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "blog资源上传",httpMethod = "POST",notes = "blog资源上传")
    public String uploadSource(MultipartFile file) {
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
     * 读取所有的上传文件并返回
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

    /**
    * @Desc:  上传blog中添加的图片等资源
    * @author: shenliqiang
    * @Time: 2019/10/31 15:19
    * @param
    * @return
    */

    @PostMapping(value="/uploadBlogSource")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "上传blog中添加的图片等资源",httpMethod = "POST",notes = "上传blog中添加的图片等资源")
    public String uploadBlogSource(MultipartFile file) {
        // 判断文件是否为空
        String filePath = "";
        String imgName = "";
        if (!file.isEmpty()) {
            try {
                // 生成文件保存路径
                imgName = Calendar.getInstance().getTime().getTime()+
                        file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                // 文件保存路径
                filePath = request.getSession().getServletContext().getRealPath("/")+imgName;
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "http://127.0.0.1:8082/"+imgName;
    }
}