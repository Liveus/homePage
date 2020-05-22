package com.liveus.core.analyze.controller;

import com.liveus.common.annotation.PassToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2020/1/10 17:32
 * @Copyright: © Liveus
 * @Warning: for fun
 */
@Controller
public class TestController2 {

    @PassToken
    @RequestMapping("/test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView("/jvm/jvmInfo");
        return modelAndView;
    }

    @PassToken
    @RequestMapping("/test2")
    public void test2(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://www.baidu.com");
    }

    @PassToken
    @RequestMapping("/test3")
    public ModelAndView test3(ModelAndView view) {
        view.setViewName("redirect:/test2");
        return view;
    }
}
