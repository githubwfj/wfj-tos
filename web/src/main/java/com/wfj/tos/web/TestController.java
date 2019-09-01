package com.wfj.tos.web;

import com.wfj.tos.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/sendAsyncMsg")
    public Object sendAsyncMsg() {
        System.out.println("####TestController####   1");

        testService.asyncMsg("async msg");

        System.out.println("####TestController####   4");

        return "success";
    }

    @GetMapping("/sendAsyncMsg/{msg}")
    public Object sendAsyncMsg1(@PathVariable("msg") String msg) throws ExecutionException, InterruptedException {
        System.out.println("####TestController####   1");

        Future<String> future = testService.asyncMsg1(msg);
        System.out.println("future: " + future.get());

        System.out.println("####TestController####   4");

        return "success";
    }
}
