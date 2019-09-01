package com.wfj.tos.service.impl;

import com.wfj.tos.service.TestService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class TestServiceImpl implements TestService {


    @Async
    @Override
    public void asyncMsg(String msg) {
        System.out.println("####asyncMsg####   2");
        IntStream.range(0, 5).forEach(d -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("####asyncMsg####   3");
    }
}
