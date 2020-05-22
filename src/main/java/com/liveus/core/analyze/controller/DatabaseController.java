package com.liveus.core.analyze.controller;

import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2020/1/13 14:53
 * @Copyright: © Liveus
 * @Warning: for fun
 */
@RestController("/database")
public class DatabaseController {
    BigDecimal bigDecimal = new BigDecimal("15");


    public void test(HttpServletRequest request){
        request.getHeader("1");
    }

}


