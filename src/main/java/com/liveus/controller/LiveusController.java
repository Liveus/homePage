package com.liveus.controller;

import com.liveus.pojo.entity.Liveus;
import com.liveus.service.LiveusService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/Liveus")
@RestController
public class LiveusController {

    @Autowired
    LiveusService liveusService;

    @RequestMapping(value = "/update"/*,method = RequestMethod.POST*/)
    @ApiOperation(value = "更新个人信息",httpMethod = "POST",notes = "更新个人信息")
    @ApiParam(required = true,name = "Liveus",value = "个人信息实体类")
    public void updateLiveus(/*@RequestParam Liveus liveus*/) throws JSONException {
        Liveus liveus = new Liveus();
        liveus.setId(0);
        liveus.setPic("default.png");
        liveus.setNickname("Liveus");
        liveus.setGithub("https://github.com/Liveus");
        liveus.setJianshu("https://www.jianshu.com/u/a80b8e3299cc");
        liveus.setJuejin("https://juejin.im/user/5b72f890f265da27e879bbb7");
        liveus.setCsdn("https://blog.csdn.net/LoveRestart");
        liveus.setMoto("Liveus's name!");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1","java");
        jsonObject.put("2","SpringBoot");
        jsonObject.put("3","Redis");
        jsonObject.put("4","RabbitMQ");
        jsonObject.put("5","MySQL");
        jsonObject.put("6","Vue");
        jsonObject.put("7","ssm");
        liveus.setSkills(jsonObject.toString());
        liveusService.updateLiveus(liveus);
    }
}
