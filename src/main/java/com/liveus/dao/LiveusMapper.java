package com.liveus.dao;

import com.liveus.domain.Liveus;
import com.liveus.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface LiveusMapper extends MyMapper<Liveus> {

    @Override
    int updateByPrimaryKey(Liveus record);
}