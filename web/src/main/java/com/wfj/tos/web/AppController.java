package com.wfj.tos.web;

import org.apache.catalina.startup.WebAnnotationSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/ok")
    public Object ok() {
        return "ok";
    }


}
