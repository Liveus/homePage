package com.liveus.core.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.liveus.core.user.mapper.LoginLogMapper;
import com.liveus.core.user.pojo.entity.LoginLogEntity;
import com.liveus.core.user.service.LoginLogService;
import org.springframework.stereotype.Service;

/**
 * @Desc: 登陆日志
 * @author: Lenovo
 * @Time: 2019/11/7 16:57

 */
@Service
public class LoginLogServiceImpl  extends ServiceImpl<LoginLogMapper, LoginLogEntity> implements LoginLogService {

}
