package com.liveus.common.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/12/17 15:25
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class WebSocketHandler extends AbstractWebSocketHandler {

    /**
     *  存储sessionId和webSocketSession
     *  需要注意的是，webSocketSession没有提供无参构造，不能进行序列化，也就不能通过redis存储
     *  在分布式系统中，要想别的办法实现webSocketSession共享
     */
    private static Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    private static Map<String, String> companyMap = new ConcurrentHashMap<>();

    /**
     * webSocket连接创建后调用
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 获取参数
        String companyCode = String.valueOf(session.getAttributes().get("companyCode"));
        companyCode = companyCode+ Calendar.getInstance().toString();
        companyMap.put(companyCode, session.getId());
        sessionMap.put(session.getId(), session);
    }

    /**
     * 接收到消息会调用
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof TextMessage) {

        } else if (message instanceof BinaryMessage) {

        } else if (message instanceof PongMessage) {

        } else {
            System.out.println("Unexpected WebSocket message type: " + message);
        }
    }

    /**
     * 连接出错会调用
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        sessionMap.remove(session.getId());
    }

    /**
     * 连接关闭会调用
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessionMap.remove(session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 后端发送消息
     */
    public void sendMessage(String companyCode, String message){
        String sessionId = companyMap.get(companyCode);
        WebSocketSession session = sessionMap.get(sessionId);
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 后端发送消息
     */
    public void sendMessageToCompany(String companyCode, String message){
        for (Map.Entry<String, String> sessionEntry : companyMap.entrySet()) {
            System.out.println("key:"+sessionEntry.getKey());
            if(sessionEntry.getKey().startsWith(companyCode)){
                System.out.println("sending");
                WebSocketSession session = sessionMap.get(sessionEntry.getValue());
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
