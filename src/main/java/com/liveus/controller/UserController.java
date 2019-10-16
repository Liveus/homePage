package com.liveus.controller;

import com.liveus.pojo.dto.Userdto;
import com.liveus.pojo.entity.Configbean2;
import com.liveus.pojo.entity.User;
import com.liveus.service.UserService;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Value(value="${com.liveus.userName}")
    private String userName;
    @Value(value="${com.liveus.passWord}")
    private String passWord;
    @Value(value = "${com.liveus.randomValue}")
    private String randomValue;
    @Value(value = "${com.liveus.uuid}")
    private String uuid;
    @Value(value = "${spring.datasource.url}")
    private String url;
    @Resource
    UserService userService;

    @Autowired
    private Configbean2 configbean2;

    /**
     *登录操作
     **/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public Map<String,Object> login(Userdto userdto,HttpSession session){
        Map<String,Object> map =new HashMap<String,Object>();
        System.out.println(userdto);
        String userName=userdto.getUserName();
        String password=userdto.getPassWord();
        if(!userName.equals("") && !password.equals("")){
            User user =new User(userName,password);
            if(userService.userLoginWithPasswd(user)){
                session.setAttribute("user",user);
                map.put("result","success");//登陆成功
            }else{
                map.put("result","error passWord");//密码不正确
            }
        }else{
            map.put("result","username&password cann't be null");//用户名和密码不得为空
        }
        return map;
    }

    @RequestMapping("/attribute")
    public String userdefinedAttribute(){
        System.out.println(userName);
        return "userName:"+userName+"----"+"password:"+passWord;
    }

    @RequestMapping("/configBean")
    public String ConfigBean(){
        return this.configbean2.toString();
    }

    @RequestMapping(value="/ex")
    @ResponseBody
    public String error(){
        int i=5/0;
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

    @RequestMapping("/Get1")
    public void get() {
        stringRedisTemplate.opsForValue().set("test", "I'm a egg");
        stringRedisTemplate.opsForList().leftPush("forlist","list1");
        System.out.println("value");
    }

    @RequestMapping("/ContextLoads")
    public void contextLoads() {
        User user = new User();
        user.setId(11);
        user.setName("li si");
        user.setPassword("zhang san");
        stringRedisTemplate.opsForValue().set("date", user.toString());
        System.out.println(stringRedisTemplate.opsForValue().get("date"));
    }

    @RequestMapping("/Redistest")
    public void Redis(){
        long startTime=System.currentTimeMillis();
        for (int i=0;i<1000;i++){
            stringRedisTemplate.opsForValue().set(String.valueOf(i) ,new Date().toString());
        }
        long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

    @AfterReturning()
    public void doafterReturning(){

    }


}
