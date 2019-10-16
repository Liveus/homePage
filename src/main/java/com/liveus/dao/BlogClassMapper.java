package com.liveus.dao;

import com.liveus.pojo.entity.Blogtype;
import com.liveus.pojo.vo.BlogtypeVo;
import com.liveus.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface BlogClassMapper extends MyMapper<Blogtype> {

    int insertNewBlog(@Param("type") String type,@Param("blogsum")Integer blogsum);

    List<BlogtypeVo> getAllType();
}