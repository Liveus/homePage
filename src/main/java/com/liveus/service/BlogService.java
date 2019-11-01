package com.liveus.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.liveus.pojo.dto.BlogDto;
import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.vo.BlogRecommendVo;
import com.liveus.pojo.vo.BlogVo;

import java.util.List;

public interface BlogService extends IService<Blog> {
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
     * 分页查询
     * @param page
     * @param dto
     * @return
     */
    Page<BlogVo> queryByPageList(Page<BlogVo> page, BlogDto dto);

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

    /**
    * @Desc:  根据type获取推荐blog
    * @author: shenliqiang
    * @Time: 2019/11/1 17:14
    * @param null
    * @return
    */

    List<BlogRecommendVo> getRecommendedBlog(Integer type, Integer id);
}
