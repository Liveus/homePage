package com.liveus.core.user.service.impl;

import com.liveus.core.user.mapper.UserMapper;
import com.liveus.core.user.pojo.entity.UserEntity;
import com.liveus.core.user.service.UserService;
import com.liveus.common.utils.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean userLoginWithPasswd(UserEntity userEntity) {
        String passwdRedis = stringRedisTemplate.opsForValue().get(userEntity.getName());//redis中的密码
        String passwdRequest = AESUtils.AESEncode(userEntity.getPassword());//请求中的密码
        String passwdSql ;
        // 缓存有值
        if(passwdRedis != null){
            // 缓存正确
            if(passwdRedis.equals(passwdRequest)){
                return true;
            } else {
                passwdSql = userMapper.loginBypasswds(userEntity.getName());
                // 无用户
                if(passwdSql == null){
                    return false;
                }else {
                    // 密码是否正确
                    return passwdSql.equals(passwdRequest);
                }
            }
        // 缓存无值
        }else {
            passwdSql = userMapper.loginBypasswds(userEntity.getName());
            // 无用户
            if(passwdSql == null){
                return false;
            }else {
                // 密码是否正确
                return passwdSql.equals(passwdRequest);
            }
        }
    }
}
