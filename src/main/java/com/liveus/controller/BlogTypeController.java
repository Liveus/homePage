package com.liveus.controller;

import com.liveus.service.BlogTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/blogType")
@RestController
public class BlogTypeController {

    @Autowired
    BlogTypeService blogTypeService;

    @ResponseBody
    @RequestMapping(value = "/newType"/*,method = RequestMethod.POST*/)
    @ApiOperation(value = "增加新blog类型",httpMethod = "POST",notes = "增加新blog类型")
    @ApiParam(name = "newType",value = "新类型",required = true)
    public String addNewType(@RequestParam String newType){
        int result = blogTypeService.addNewType(newType);
        if(result==1){
            return "新增成功!";
        }else{
            return "新增失败!";
        }
    }

/*    @ResponseBody
    @RequestMapping(value = "/getAll")
    @ApiOperation(value = "")
    public List<Blogtype> getAllTypes(){

    }*/
}
