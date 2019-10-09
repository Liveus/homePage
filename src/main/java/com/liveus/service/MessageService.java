package com.liveus.service;

import com.liveus.pojo.entity.Message;

import java.util.List;

public interface MessageService {
    /**
     * 增加新留言
     * @param message
     * @return
     */
    String newMessage(Message message);

    /**
     * 获取所有留言
     * @return
     */
    List<Message> getAllMessagesOrderByTime();

    /**
     * 监测当前ip最新的留言
     * @param ip
     * @return
     */
    String ipMonitoring(String ip);

}
