package com.liveus.service;

import com.liveus.domain.User;

public interface UserService {

    /**
     * 密码登陆
     * @param user
     * @return
     */
    boolean userLoginWithPasswd(User user);
}
