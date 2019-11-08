package com.liveus.core.user.mapper;

import com.liveus.core.user.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    String loginBypasswds(String name);

    UserEntity selectByUserId(@Param("id") Integer id);

    UserEntity selectByUsername(@Param("username") String username);
}