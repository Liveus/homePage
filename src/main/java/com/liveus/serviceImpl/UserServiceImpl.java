package com.liveus.serviceImpl;

import com.liveus.dao.UserMapper;
import com.liveus.domain.User;
import com.liveus.service.UserService;
import com.liveus.utils.AES;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource
    UserMapper userMapper;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean userLoginWithPasswd(User user) {
        String passwoRedis = stringRedisTemplate.opsForValue().get(user.getName());//redis中的密码
        String passwdRequest = AES.encrypt(user.getPassword());//请求中的密码
        if(passwdRequest.equals(passwoRedis)){
            return true;
        }else if(userMapper.loginBypasswd(user.getName()).equals(AES.encrypt(user.getPassword()))){
            stringRedisTemplate.opsForValue().set(user.getName(), AES.encrypt(user.getPassword()));//存入redis
            return true;
        }
        return false;
    }
}
