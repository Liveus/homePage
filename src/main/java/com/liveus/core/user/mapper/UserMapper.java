package com.liveus.core.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    String loginBypasswds(String name);

}