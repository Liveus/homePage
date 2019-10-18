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
        String passwdRedis = stringRedisTemplate.opsForValue().get(user.getName());//redis中的密码
        String passwdRequest = AESUtils.AESEncode(user.getPassword());//请求中的密码
        String passwdSql ;
        // 缓存有值
        if(passwdRedis != null){
            // 缓存正确
            if(passwdRedis.equals(passwdRequest)){
                return true;
            } else {
                passwdSql = userMapper.loginBypasswds(user.getName());
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
            passwdSql = userMapper.loginBypasswds(user.getName());
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
