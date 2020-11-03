package com.liveus.core.blog.dao.impl;

import com.liveus.config.CacheClient;
import com.liveus.core.blog.dao.BlogClassDao;
import com.liveus.core.blog.mapper.BlogClassMapper;
import com.liveus.core.user.pojo.vo.BlogtypeVo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Package: com.liveus.core.blog.dao.impl
 * @Author: shen2
 * @Description:
 * @Date: 2020/11/3 14:14
 */
@Repository
public class BlogClassDaoImpl implements BlogClassDao {

    @Autowired
    BlogClassMapper blogClassMapper;

    @Autowired
    private CacheClient cacheClient;

    /**
     * 缓存key前缀
     */
    private static final String  PERFORM_FREQ_DICT_PREFIX = "BLOG_CLASS:";

    /**
     * 缓存时间
     * 单位：秒
     */
    private static final long CACHE_TIME = 12 * 60 * 60L;

    @Override
    public List<BlogtypeVo> getAllType(){
        List<BlogtypeVo> blogtypeVoList = cacheClient.lGet(getKeyByAllList(), 0, -1, BlogtypeVo.class);
        if (CollectionUtils.isNotEmpty(blogtypeVoList)) {
            return blogtypeVoList;
        }
        blogtypeVoList = blogClassMapper.getAllType();
        if (Objects.nonNull(blogtypeVoList)) {
            cacheClient.lSet(getKeyByAllList(),blogtypeVoList,CACHE_TIME, TimeUnit.SECONDS);
        }
        return blogtypeVoList;
    }

    /**
     * 获取缓存key
     * @return
     */
    private String getKeyByAllList() {
        return PERFORM_FREQ_DICT_PREFIX + "ALL";
    }
}
