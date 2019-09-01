package com.wfj.tos.server2;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;


public class MyBinaryWebSocketHandler extends BinaryWebSocketHandler {

    protected void handleTextMessage(WebSocketSession session, BinaryMessage message) {

    }
}
