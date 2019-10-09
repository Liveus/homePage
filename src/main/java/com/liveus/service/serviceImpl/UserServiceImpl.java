package com.liveus.service.serviceImpl;

import com.liveus.dao.UserMapper;
import com.liveus.pojo.entity.User;
import com.liveus.service.UserService;
import com.liveus.utils.AESUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean userLoginWithPasswd(User user) {
        String passwoRedis = stringRedisTemplate.opsForValue().get(user.getName());//redis中的密码
        String passwdRequest = AESUtils.AESEncode(user.getPassword());//请求中的密码
        if(passwdRequest.equals(passwoRedis)){
            return true;
        }else if(userMapper.loginBypasswds(user.getName()).equals(AESUtils.AESEncode(user.getPassword()))){
            stringRedisTemplate.opsForValue().set(user.getName(), AESUtils.AESEncode(user.getPassword()),100, TimeUnit.SECONDS);//存入redis
            return true;
        }
        return false;
    }
}
