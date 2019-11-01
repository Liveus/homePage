package com.liveus.dao;

import com.liveus.pojo.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface MessageMapper {

    int insert(Message record);

    List<Message> selectAll();

    String ipmonitoring(String ip);
}