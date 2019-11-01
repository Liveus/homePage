package com.liveus.controller;

import com.liveus.enums.CommonStatus;
import com.liveus.pojo.dto.Userdto;
import com.liveus.pojo.entity.Configbean2;
import com.liveus.pojo.entity.User;
import com.liveus.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;
    @Resource
    private Configbean2 configbean2;

    @Value(value = "${spring.datasource.url}")
    private String url;


    /**
     *登录操作
     **/
    @PostMapping(value = "/login")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "登陆",httpMethod = "POST",notes = "")
    public Map<String,Object> login(Userdto userdto,HttpSession session){
        Map<String,Object> map =new HashMap<String,Object>();
        String userName=userdto.getUserName();
        String password=userdto.getPassWord();
        if(!userName.equals("") && !password.equals("")){
            User user =new User(userName,password);
            if(userService.userLoginWithPasswd(user)){
                // 暂定session
                session.setAttribute("user",user);
                map.put("result","success");//登陆成功
                // 模拟token
                map.put("token","testdasdas");//登陆成功
            }else{
                map.put("result","error username or passWord");//密码不正确
            }
        }else{
            map.put("result","username&password cann't be null");//用户名和密码不得为空
        }
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
