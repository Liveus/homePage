package com.liveus.core.blog.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.liveus.common.annotation.UserLoginToken;
import com.liveus.common.utils.FtpUtil;
import com.liveus.common.utils.UploadUtils;
import com.liveus.config.FtpConfig;
import com.liveus.core.blog.pojo.dto.BlogDto;
import com.liveus.core.blog.pojo.entity.Blog;
import com.liveus.core.user.pojo.vo.BlogRecommendVo;
import com.liveus.core.user.pojo.vo.BlogVo;
import com.liveus.core.blog.service.BlogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@RequestMapping("/blog")
@RestController
public class BlogController {

    @Resource
    BlogService blogService;

    /**
     * 上传新blog
     *
     * @param blog
     */
    @UserLoginToken
    @PostMapping("/submit")
    @ResponseBody
    @ApiOperation(value = "上传blog", httpMethod = "POST", notes = "上传blog")
    public void submit(Blog blog, @RequestParam("newBlogClass") List<String> newBlogClass) {
        var a = 1; //设置默认为1
        blog.setCreateBy(a);
        blogService.newBlog(blog, newBlogClass);
    }

    /**
    * @Desc:  分页查询
    * @author: shenliqiang
    * @Time: 2019/12/6 10:55
    * @param dto
    * @return:
    */
    @GetMapping(value = "/getBlog")
    @ResponseBody
    @ApiOperation(value = "分页查询", httpMethod = "GET", notes = "分页查询")
    public Page<BlogVo> queryByPageList(@ModelAttribute BlogDto dto) {
        dto.setIsfinished(2);
        return this.blogService.queryByPageList(new Page<>(dto.getPage(), dto.getPageSize()), dto);
    }

    /**
     * @param dto
     * @Desc: 管理--获取所有blog
     * @author: shenliqiang
     * @Time: 2019/11/8 14:54
     * @return:
     */

    @UserLoginToken
    @GetMapping(value = "/manageBlog")
    @ResponseBody
    @ApiOperation(value = "分页查询", httpMethod = "GET", notes = "分页查询")
    public Page<BlogVo> queryByPageListManage(@ModelAttribute BlogDto dto) {
        dto.setIsfinished(1);
        return this.blogService.queryByPageList(new Page<>(dto.getPage(), dto.getPageSize()), dto);
    }

    /**
     * 获取当前用户所有blog标题
     *
     * @return:
     */
    @PostMapping(value = "/searchTitle")
    @ResponseBody
    public List<String> getTitles(@RequestParam("searchTitle") String searchTile) {
        return blogService.getTitles(searchTile);
    }

    /**
     * 获取指定id的blog
     *
     * @param blogId
     * @return:
     */
    @GetMapping("/getBlogById/{blogId}")
    @ResponseBody
    @ApiOperation(value = "获取blog详细内容", httpMethod = "GET", notes = "获取blog详细内容")
    @ApiParam(name = "blogId", value = "Integer", required = true)
    public Blog getBlogById(@PathVariable("blogId") Integer blogId) {
        return blogService.getBlogById(blogId);
    }

    /**
     * 获取推荐的blog
     * @param type,value
     * @Desc:
     * @author: shenliqiang
     * @Time: 2019/11/1 17:05
     * @return:
     */
    @GetMapping("/getRecommend")
    @ResponseBody
    @ApiOperation(value = "获取推荐的blog", httpMethod = "GET", notes = "获取推荐的blog")
    public List<BlogRecommendVo> getRecommendBlog(@ModelAttribute("type") Integer type, @ModelAttribute("value") String value) {
        return this.blogService.getRecommendedBlog(type, value);
    }

    /**
     * @param file
     * @Desc: 上传blog附件中的资源
     * @author: shenliqiang
     * @Time: 2019/10/31 15:19
     * @return:
     */
    @PostMapping(value = "/uploadSource")
    @ResponseBody
    @ApiOperation(value = "blog资源上传", httpMethod = "POST", notes = "blog资源上传")
    public String uploadSource(MultipartFile file) throws IOException {
        FtpConfig ftpConfig = new FtpConfig();
        String oldName = file.getOriginalFilename();// 获取图片原来的名字
        String picNewName = UploadUtils.generateRandomFileName(oldName);// 通过工具类产生新图片名称，防止重名
        String picSavePath = "/Source";//保存路径
        return  FtpUtil.pictureUploadByConfig(ftpConfig, picNewName, picSavePath, file.getInputStream());// 上传到图片服务器的操作
    }

    /**
     * @Desc: 上传blog中添加的图片等资源
     * @author: shenliqiang
     * @Time: 2019/10/31 15:19
     * @return:
     */
    @PostMapping("/uploadBlogSource")
    public String uploadBlogSource(@RequestParam("file") MultipartFile file) throws IOException {
        FtpConfig ftpConfig = new FtpConfig();
        String oldName = file.getOriginalFilename();// 获取图片原来的名字
        String picNewName = UploadUtils.generateRandomFileName(oldName);// 通过工具类产生新图片名称，防止重名
        String picSavePath = "/BlogSource";//保存路径
        return  FtpUtil.pictureUploadByConfig(ftpConfig, picNewName, picSavePath, file.getInputStream());// 上传到图片服务器的操作
    }

    /**
     * 下载文件（ftp + nginx)（单个文件下载）
     * @param remotePath 绝对路径或相对路径
     * @param fileName   文件名
     * @param localPath  下载后保存到本地的路径
     * @throws IOException for example:
     *                     remotePath="/home/ftpuser/upload/picture/18083764688"+
     *                     fileName="33691e23-b864-4069-a83c-2427c27cdd96.jpg"+
     *                     localPath="C:\Users\zj\Desktop"
     */
    @PostMapping("/ftp/download")
    public void downloadHeadImgNew(@RequestParam("remotePath") String remotePath, @RequestParam("fileName") String fileName, @RequestParam("localPath") String localPath) throws IOException {
        FtpConfig ftpConfig = new FtpConfig();
        FtpUtil.pictureDownloadByConfig(ftpConfig, remotePath, fileName, localPath);
        System.out.println("文件下载成功");
    }

    /**
     * 暂无使用-------------------------------------
     * 获取当前用户所有blog
     *
     * @return:
     */
    @PostMapping(value = "/allBlog")
    @ResponseBody
    public List<Blog> getAllBlog(BlogDto dto) {
        return blogService.getBlogs(dto);
    }

    /**
     * ---------------已废弃
     * @Desc: 上传blog中添加的图片等资源
     * @author: shenliqiang
     * @Time: 2019/10/31 15:19
     * @return:
     */
/*    @PostMapping(value = "/uploadBlogSource")
    @ResponseBody

    @ApiOperation(value = "上传blog中添加的图片等资源", httpMethod = "POST", notes = "上传blog中添加的图片等资源")
    public String uploadBlogSource(MultipartFile file) {
        // 判断文件是否为空
        String filePath = "";
        String imgName = "";
        if (!file.isEmpty()) {
            try {
                // 生成文件保存路径
                imgName = Calendar.getInstance().getTime().getTime() +
                        file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                // 文件保存路径
                filePath = request.getSession().getServletContext().getRealPath("/") + imgName;
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "http://127.0.0.1:8082/" + imgName;
    }*/


    /**
     * ---------------已废弃
     * 读取所有的上传文件并返回
     * @return:
     */
/*    @RequestMapping("list")
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
    }*/
}