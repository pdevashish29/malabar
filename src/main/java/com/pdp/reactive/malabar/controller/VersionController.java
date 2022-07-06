package com.pdp.reactive.malabar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public String getVersion(){
        return environment.getProperty("spring.application.name")+"\tis running with version "+environment.getProperty("spring.application.version");
    }

}
