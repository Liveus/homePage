package com.liveus.core.user.controller;

import com.liveus.common.utils.AESUtils;
import com.liveus.common.utils.JwtUtils;
import com.liveus.core.sys.enums.CommonStatus;
import com.liveus.core.user.pojo.dto.Userdto;
import com.liveus.config.ConfigBean;
import com.liveus.core.user.pojo.entity.LoginLogEntity;
import com.liveus.core.user.pojo.entity.UserEntity;
import com.liveus.core.user.service.LoginLogService;
import com.liveus.core.user.service.UserService;
import com.liveus.common.utils.IpDetective;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private Environment evn;

    @Autowired
    UserService userService;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *登录操作
     **/
    @PostMapping(value = "/login")
    @ResponseBody

    @ApiOperation(value = "登陆",httpMethod = "POST",notes = "")
    public Map<String,Object> login(Userdto userdto, HttpSession session, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String responseMsg = "";
        if(!userdto.getUserName().equals("") && !userdto.getPassWord().equals("")){
            UserEntity userEntity =new UserEntity(userdto.getUserName(),userdto.getPassWord());
            if(userService.userLoginWithPasswd(userEntity)){
                userEntity = userService.findUserByUsername(userdto.getUserName());
                session.setAttribute("user", userEntity);
                map.put("result","success");
                map.put("token", JwtUtils.generateToken(userEntity.getId()));
                map.put("user", userEntity);
                responseMsg = "login success";
            }else{
                map.put("result","error username or passWord");//密码不正确
                responseMsg = "error username or passWord";
            }
        }else{
            map.put("result","username&password can not be null");//用户名和密码不得为空
            responseMsg = "username&password can not be null";
        }
        // 记录登陆操作
        this.loginLogService.insert(new LoginLogEntity(userdto.getUserName(), AESUtils.AESEncode(userdto.getPassWord())
        ,responseMsg,IpDetective.getIpAddr(request),"1", Calendar.getInstance().getTime(),1));
        return map;
    }

    @GetMapping(value = "/logout")
    @ResponseBody

    @ApiOperation(value = "登出操作",httpMethod = "GET",notes = "")
    public CommonStatus logout(HttpServletRequest httpServletRequest,HttpSession session){
        String token = httpServletRequest.getHeader("token");
        UserEntity user = userService.findUserById(JwtUtils.verifyJwt(token).get("userId",Integer.class));
        session.removeAttribute("user");
        this.loginLogService.insert(new LoginLogEntity(user.getName(), "","logout success",
                IpDetective.getIpAddr(httpServletRequest),"1", Calendar.getInstance().getTime(),2));
        return CommonStatus.LOGOUT_OK;
    }

    // 抛出除0异常
    @RequestMapping(value="/ex")
    @ResponseBody
    public String error(){
        int i= 5/0;
        return "ex";
    }

    //局部异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exHandler(Exception e){
        // 判断发生异常的类型是除0异常则做出响应
        if(e instanceof ArithmeticException){
            return "发生了除0异常";
        }
        // 未知的异常做出响应
        e.printStackTrace();
        return "发生了未知异常";
    }

    @AfterReturning()
    public void doAfterReturning(){
        logger.info("afterReturning");
    }
}
