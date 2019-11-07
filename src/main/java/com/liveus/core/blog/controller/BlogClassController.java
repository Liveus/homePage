package com.liveus.core.blog.controller;

import com.liveus.core.user.pojo.vo.BlogtypeVo;
import com.liveus.core.blog.service.BlogClassService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/blogClass")
@RestController
public class BlogClassController {


    private BlogClassService blogClassService;

    @Autowired
    public BlogClassController(BlogClassService blogClassService) {
        this.blogClassService = blogClassService;
    }

    @ResponseBody
    @PostMapping(value = "/newClass")
    @ApiOperation(value = "增加分类专栏",httpMethod = "POST",notes = "增加分类专栏")
    @ApiParam(name = "newType",value = "新类型",required = true)
    @CrossOrigin
    public String addNewType(@RequestParam String newType){
        int result = blogClassService.addNewType(newType);
        if(result==1){
            return "新增成功!";
        }else{
            return "新增失败!";
        }
    }

    @ResponseBody
    @GetMapping(value = "/getAll")
    @ApiOperation(value = "获取所有分类专栏",httpMethod = "GET",notes = "增加分类专栏")
    @CrossOrigin
    public List<BlogtypeVo> getAllTypes(){
        return this.blogClassService.getAllType();
    }
}
