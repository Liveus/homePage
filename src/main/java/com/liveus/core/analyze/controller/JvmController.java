package com.liveus.core.analyze.controller;

import com.alibaba.fastjson.JSONObject;
import com.liveus.core.analyze.service.JvmService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Desc: jvm参数获取
 * @author: Lenovo
 * @Time: 2019/11/13 11:00
 * @Copyright: © Liveus
 * @Warning: for fun
 */

@RestController
@RequestMapping("/jvm")
public class JvmController {

    @Autowired
    private JvmService jvmService;

    @GetMapping("/jvmInfo")
    @ResponseBody
    @CrossOrigin
    @ApiOperation(value = "获取jvm参数",httpMethod = "GET",notes = "")
    public JSONObject getJvmInfo(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(this.jvmService.getJvmInfo());
        return jsonObject;
    }
}
