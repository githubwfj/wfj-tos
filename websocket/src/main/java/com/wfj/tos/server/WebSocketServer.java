package com.wfj.tos.server;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/ws/connect")
public class WebSocketServer {

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
}
