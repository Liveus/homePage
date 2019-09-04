package com.liveus.dao;

import com.liveus.domain.Message;
import com.liveus.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface MessageMapper extends MyMapper<Message> {
    @Override
    int insert(Message record);

    @Override
    List<Message> selectAll();

    String ipmonitoring(String ip);
}