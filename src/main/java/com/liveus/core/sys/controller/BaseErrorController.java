package com.liveus.core.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

/**
* @Desc:  basic error
* @author: shenliqiang
* @Time: 2019/11/8 17:17
*/

@Controller
public class BaseErrorController{

    // 抛出除0异常
    @RequestMapping("/json")
    public void json(ModelMap modelMap) {
        System.out.println(modelMap.get("author"));
        int i=5/0;
    }
}
