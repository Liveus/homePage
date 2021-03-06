package com.liveus.core.blog.service;

import com.liveus.core.user.pojo.vo.BlogtypeVo;

import java.util.List;

public interface BlogClassService {
    /**
     * 增加新类型
     * @param type
     * @return
     */
    int addNewType(String type);

    /**
     * 获取所有blog类型
     * @return
     */
    List<BlogtypeVo>  getAllType();
}
