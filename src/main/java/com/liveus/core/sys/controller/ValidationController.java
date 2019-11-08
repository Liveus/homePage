package com.liveus.core.sys.controller;

import com.liveus.common.Validator.ValidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
* @Desc:  spring validation 验证
* @author: shenliqiang
* @Time: 2019/11/8 17:16
*/

@RestController
@RequestMapping("/validate")
public class ValidationController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/validator")
    public String validator(@Valid ValidateEntity entity, BindingResult result){
        if(result.hasErrors()){
            StringBuffer msg = new StringBuffer();
            List<FieldError> fieldErrors = result.getFieldErrors();
            Locale locale = LocaleContextHolder.getLocale();
            for(FieldError fieldError:fieldErrors){
                String errormsg = messageSource.getMessage(fieldError,locale);
                msg.append(fieldError.getField()+":"+errormsg+",");
            }
            return msg.toString();
        }
        return "验证通过:"+ "名称:"+entity.getName()+"年龄:"+entity.getAge()+"邮箱:"+entity.getEmail()+"falg:"+entity.getFlag();
    }
}
