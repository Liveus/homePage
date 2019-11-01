package com.liveus.dao;

import com.liveus.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    String loginBypasswds(String name);

}