package com.liveus.dao;

import com.liveus.domain.User;
import com.liveus.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {

    String loginBypasswd(String name);
}