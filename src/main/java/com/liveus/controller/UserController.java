package com.liveus.controller;

import com.liveus.domain.Configbean2;
import com.liveus.domain.User;
import com.liveus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

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

    @Value(value = "${spring.datasource.password}")
    private String pad;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    UserService userService;
    @Resource
    RedisTemplate redisTemplate;

    /**
     *登录操作
     **/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map =new HashMap<String,Object>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        if(!userName.equals("") && password!=""){
            User user =new User(userName,password);
            if(userService.userLoginWithPasswd(user)){
                request.getSession().setAttribute("user",user);
                map.put("result","1");//登陆成功
            }else{
                map.put("result","2");//密码不正确
            }
        }else{
            map.put("result","0");//用户名和密码不得为空
        }
        return map;
    }

    @Autowired
    private Configbean2 configbean2;

    @RequestMapping("/uuid")
    public String randomUuid(){
        System.out.println(url);
        System.out.println(pad);
        System.out.println("".isBlank());
        System.out.println(" Javastack ".strip());
        System.out.println(" Javastack ".stripTrailing());
        System.out.println(" Javastack ".stripLeading());
        System.out.println("Java".repeat(3));
        System.out.println("A\nB\nC".lines().count());
        return uuid;
    }

    @RequestMapping("/randomValue")
    public String randomValue(){
        return randomValue;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
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
        System.out.println(stringRedisTemplate.opsForValue().get("test"));
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


}
