package com.liveus.core.user.controller;

import com.liveus.common.utils.AESUtils;
import com.liveus.core.sys.enums.CommonStatus;
import com.liveus.core.user.pojo.dto.Userdto;
import com.liveus.common.bean.Configbean2;
import com.liveus.core.user.pojo.entity.LoginLogEntity;
import com.liveus.core.user.pojo.entity.UserEntity;
import com.liveus.core.user.service.LoginLogService;
import com.liveus.core.user.service.UserService;
import com.liveus.common.utils.IpDetective;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    UserService userService;

    @Autowired
    LoginLogService loginLogService;

    @Autowired
    private Configbean2 configbean2;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Value(value = "${spring.datasource.url}")
    private String url;

    /**
     *登录操作
     **/
    @PostMapping(value = "/login")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "登陆",httpMethod = "POST",notes = "")
    public Map<String,Object> login(Userdto userdto, HttpSession session, HttpServletRequest request){
        // 登陆
        Map<String,Object> map = new HashMap<String,Object>();
        String responseMsg = "";
        if(!userdto.getUserName().equals("") && !userdto.getPassWord().equals("")){
            UserEntity userEntity =new UserEntity(userdto.getUserName(),userdto.getPassWord());
            if(userService.userLoginWithPasswd(userEntity)){
                // 暂定session
                session.setAttribute("user", userEntity);
                map.put("result","success");
                // 模拟token
                map.put("token","TestToken");
                // put user
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
        ,responseMsg,IpDetective.getIpAddr(request),"1", Calendar.getInstance().getTime()));
        return map;
    }

    @GetMapping(value = "/logout")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "登出操作",httpMethod = "GET",notes = "")
    public CommonStatus logout(){
        //具体步骤代写
        return CommonStatus.LOGOUT_OK;
    }

    @RequestMapping("/configBean")
    public String ConfigBean(){
        return this.configbean2.toString();
    }

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
    public void doafterReturning(){
    }
}
