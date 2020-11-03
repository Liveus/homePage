package com.liveus.core.blog.dao;

import com.liveus.core.user.pojo.vo.BlogtypeVo;

import java.util.List;

/**
 * @Package: com.liveus.core.blog.dao
 * @Author: shen2
 * @Description:
 * @Date: 2020/11/3 14:14
 */
public interface BlogClassDao {

    List<BlogtypeVo> getAllType();

}
