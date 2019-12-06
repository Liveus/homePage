package com.liveus.common.Nmea.socket;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/22 15:17
 * @Copyright: © Liveus
 * @Warning: for fun
 */
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient {
    public static void main(String[] args) throws Exception {
        // 要连接的服务端IP地址和端口
        String host = "127.0.0.1";
        int port = 55533;
        // 与服务端建立连接
        Socket socket = new Socket(host, port);
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
        String message="你好  yiwangzhibujian";
        socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
        outputStream.close();
        socket.close();
    }
}
