package com.liveus.core.blog.mapper;

import com.liveus.core.user.pojo.vo.BlogtypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BlogClassMapper{

    int insertNewBlog(@Param("type") String type,@Param("blogsum")Integer blogsum);

    List<BlogtypeVo> getAllType();

    String selectById(@Param("id") Integer id);

    Integer selectByName(@Param("name")String name);
}