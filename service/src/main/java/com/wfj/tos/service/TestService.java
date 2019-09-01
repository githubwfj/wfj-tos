package com.wfj.tos.service;

import java.util.concurrent.Future;

public interface TestService {

    /**
     * 异步消息
     *
     * @param msg string
     */
    void asyncMsg(String msg);

    /**
     * 异步消息
     *
     * @param msg string
     */
    Future<String> asyncMsg1(String msg);
}
