package com.liveus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class BaseErrorController{
    @RequestMapping("/json")
    public void json(ModelMap modelMap) {
        var a = 0.023;
        BigDecimal b = new BigDecimal(0.023);
        System.out.println(modelMap.get("author"));
        int i=5/0;
    }
}
