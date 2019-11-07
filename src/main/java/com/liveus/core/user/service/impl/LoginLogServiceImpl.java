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
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Service
public class LoginLogServiceImpl  extends ServiceImpl<LoginLogMapper, LoginLogEntity> implements LoginLogService {

}
