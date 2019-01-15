package com.liveus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    /**
     * 主页访问
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView ToIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping("/VueTest")
    public ModelAndView VueTest(){
        ModelAndView modelAndView = new ModelAndView("VueTest");
        return modelAndView;
    }
}
