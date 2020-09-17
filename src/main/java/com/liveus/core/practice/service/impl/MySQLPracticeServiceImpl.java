package com.liveus.core.practice.service.impl;

import com.liveus.core.practice.mapper.MySQLMapper;
import com.liveus.core.practice.pojo.dto.ExplainDto;
import com.liveus.core.practice.pojo.dto.OptimizerTraceDto;
import com.liveus.core.practice.pojo.entity.Explain;
import com.liveus.core.practice.pojo.entity.OptimizerTrace;
import com.liveus.core.practice.pojo.vo.ExplainVo;
import com.liveus.core.practice.pojo.vo.OptimizerTraceVo;
import com.liveus.core.practice.service.MySQLPracticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.liveus.core.practice.service.impl
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/15 15:48
 */
@Service
public class MySQLPracticeServiceImpl implements MySQLPracticeService {

    @Autowired
    private MySQLMapper mySQLMapper;

    @Override
    public List<ExplainVo> explainMySQL(ExplainDto dto) {
        //查询
        // TODO: 2020/9/16 防sql注入 --增加切面
        List<Explain> list = mySQLMapper.explainQuery(dto);
        //数据处理
        List<ExplainVo> result = new ArrayList<>(list.size());
        if(list.size()>0){
            for (Explain explain:list) {
                ExplainVo explainVo = new ExplainVo();
                BeanUtils.copyProperties(explain,explainVo);
                result.add(explainVo);
            }
        }else {
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public List<OptimizerTraceVo> OptimizerTraceMySQL(OptimizerTraceDto dto) {
        //SET optimizer_trace="enabled=on";开启

        //SQL执行
        List<OptimizerTrace> list = mySQLMapper.optimizerTraceQuery(dto);

        //SELECT * FROM information_schema.OPTIMIZER_TRACE;查询

        //SET optimizer_trace="enabled=off"; 关闭


        List<OptimizerTraceVo> result = new ArrayList<>(list.size());
        if(list.size()>0){
            for (OptimizerTrace optimizerTrace:list) {
                OptimizerTraceVo vo = new OptimizerTraceVo();
                BeanUtils.copyProperties(optimizerTrace,vo);
                result.add(vo);
            }
        }else {
            return new ArrayList<>();
        }
        return result;
    }
}
