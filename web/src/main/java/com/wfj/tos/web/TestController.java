package com.wfj.tos.web;

import com.wfj.tos.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/async")
    public Object async() {
        System.out.println("####TestController####   1");

        testService.asyncMsg("async msg");

        System.out.println("####TestController####   4");

        return "success";
    }
}
