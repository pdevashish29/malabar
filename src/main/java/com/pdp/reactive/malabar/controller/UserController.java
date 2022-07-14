package com.pdp.reactive.malabar.controller;

import com.pdp.reactive.malabar.vo.MalaBarResponse;
import com.pdp.reactive.malabar.model.User;
import com.pdp.reactive.malabar.vo.UserTimeLine;
import com.pdp.reactive.malabar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public Flux<User> getUsers(){
        log.info("UserController getUsers ....");
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public Mono<MalaBarResponse<User>> getUserById(@PathVariable Long id){
        log.info("UserController getUserById ....",id);
        return userService.getUserById(id);
    }

    @GetMapping("/users-timeline/{id}")
    public Mono<MalaBarResponse<UserTimeLine>> getUserTimeLine(@PathVariable Long id){
        log.info("UserController getUserTimeLine ....",id);
        return userService.getUserTimeLine3(id);
    }



}
