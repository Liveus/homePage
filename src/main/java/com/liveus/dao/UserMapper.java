package com.liveus.dao;

import com.liveus.pojo.entity.User;
import com.liveus.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper extends MyMapper<User> {

    String loginBypasswds(String name);

}