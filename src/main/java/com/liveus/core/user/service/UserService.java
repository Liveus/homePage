package com.liveus.core.user.service;

import com.liveus.core.user.pojo.entity.UserEntity;

public interface UserService {

    /**
     * 密码登陆
     * @param userEntity
     * @return
     */
    boolean userLoginWithPasswd(UserEntity userEntity);

    UserEntity findUserById(Integer id);

    UserEntity findUserByUsername(String username);
}
