package com.liveus.core.practice.controller;

import com.liveus.common.Constant.Constant;
import com.liveus.common.local.CommonResult;
import com.liveus.core.practice.pojo.dto.ExplainDto;
import com.liveus.core.practice.pojo.dto.OptimizerTraceDto;
import com.liveus.core.practice.pojo.vo.ExplainVo;
import com.liveus.core.practice.pojo.vo.OptimizerTraceVo;
import com.liveus.core.practice.service.MySQLPracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Package: com.liveus.core.practice.controller
 * @Author: shen2
 * @Description: mysql测试
 * @Date: 2020/9/15 15:45
 */
@RestController
@RequestMapping("mysql")
public class MySQLPracticeController {

    @Autowired
    private MySQLPracticeService mySQLPracticeService;

    /**
     * 通过explain分析sql执行情况
     * @param dto
     * @return
     */
    @PostMapping("/explain")
    public CommonResult explainSQL(@RequestBody ExplainDto dto){
        List<ExplainVo> result = mySQLPracticeService.explainMySQL(dto);
        if (result.size()>0){
            return CommonResult.getSuccess(Constant.STATUS_SUCCESS,result);
        }else {
            return CommonResult.getFailure();
        }
    }

    /**
     * 通过optimizerTrace分析sql执行情况
     * @return
     */
    @PostMapping("/optimizerTrace")
    public CommonResult optimizerTraceSQL(@RequestBody OptimizerTraceDto dto){
        List<OptimizerTraceVo> result = mySQLPracticeService.OptimizerTraceMySQL(dto);
        if(result.size()>0){
            return CommonResult.getSuccess(Constant.STATUS_SUCCESS,result);
        }else {
            return CommonResult.getFailure();
        }
    }

}
