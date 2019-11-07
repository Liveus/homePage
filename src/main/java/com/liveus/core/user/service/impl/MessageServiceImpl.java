package com.liveus.core.user.service.impl;

import com.liveus.core.user.mapper.MessageMapper;
import com.liveus.core.user.pojo.entity.Message;
import com.liveus.core.user.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public String newMessage(Message message) {
        int result = messageMapper.insert(message);
        if(result==1){
            return "留言成功！";
        }else{
            return "留言失败！";
        }
    }

    @Override
    public List<Message> getAllMessagesOrderByTime() {
        List<Message> messages = messageMapper.selectAll();
        return messages;
    }

    @Override
    public String ipMonitoring(String ip) {
        String result = messageMapper.ipmonitoring(ip);
        if(null==result){
            return "noRecord";
        }
        return result;
    }
}
