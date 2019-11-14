package com.liveus.common.interceptor;

import com.liveus.common.annotation.PassToken;
import com.liveus.common.annotation.UserLoginToken;
import com.liveus.common.utils.JwtUtils;
import com.liveus.core.user.pojo.entity.UserEntity;
import com.liveus.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object object) throws Exception {
        // 1.从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 2.如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)object;
        Method method = handlerMethod.getMethod();
        // 3.检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 4.检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                UserEntity user = userService.findUserById(JwtUtils.verifyJwt(token).get("userId",Integer.class));
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//                try {
//                    jwtVerifier.verify(token);
//                } catch (JWTVerificationException e) {
//                    throw new RuntimeException("401");
//                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}