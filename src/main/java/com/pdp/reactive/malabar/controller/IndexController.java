package com.pdp.reactive.malabar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {


    @Autowired
    private Environment environment;

    @GetMapping("/")
    public String getVersion(Model model) {
        model.addAttribute("app_name",environment.getProperty("spring.application.name"));
        model.addAttribute("app_version",environment.getProperty("spring.application.version"));
        model.addAttribute("server_name",environment.getProperty("server.name"));
        return "welcome";
    }

}
