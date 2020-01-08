package com.liveus.common.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/12/17 15:26
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public class WebSocketInterceptor implements HandshakeInterceptor {

    /**
     * handler处理前调用,attributes属性最终在WebSocketSession里,可能通过webSocketSession.getAttributes().get(key值)获得
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse serverHttpResponse, org.springframework.web.socket.WebSocketHandler webSocketHandler, Map<String, Object> attributes) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
            // 获取请求路径携带的参数
            String companyCode = serverHttpRequest.getServletRequest().getParameter("companyCode");
            attributes.put("companyCode", companyCode);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, org.springframework.web.socket.WebSocketHandler webSocketHandler, Exception e) {

    }
}
