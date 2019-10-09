package com.liveus.service.serviceImpl;

import com.liveus.dao.MessageMapper;
import com.liveus.pojo.entity.Message;
import com.liveus.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
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
