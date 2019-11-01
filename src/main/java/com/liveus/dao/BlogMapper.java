package com.liveus.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.liveus.pojo.dto.BlogDto;
import com.liveus.pojo.entity.Blog;
import com.liveus.pojo.vo.BlogRecommendVo;
import com.liveus.pojo.vo.BlogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogMapper extends BaseMapper<Blog> {

    // 新增
    int insertBlog(Blog blog);

    // 查询单个blog
    Blog getBlogById(int id);

    // 模糊查询所有blog
    List<Blog> getAllBlogs(BlogDto dto);

    // 模糊查询所有标题
    List<String> getTitle(@Param("searchTitle") String searchTitle);

    // 分页
    List<BlogVo> queryByPageList(Pagination page, Map<String,Object> map);

    // 推荐相邻时间
    List<BlogRecommendVo> getRecommendedBlogByTime(Integer id);

    // 推荐相似分类
    List<BlogRecommendVo> getRecommendedBlogByClass(Integer id);

    // 推荐相似标签
    List<BlogRecommendVo> getRecommendedBlogByTag(Integer id);

}