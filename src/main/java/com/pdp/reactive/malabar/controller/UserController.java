package com.pdp.reactive.malabar.controller;

import com.pdp.reactive.malabar.model.MalaBarResponse;
import com.pdp.reactive.malabar.model.User;
import com.pdp.reactive.malabar.model.UserTimeLine;
import com.pdp.reactive.malabar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Flux<User> getPersons(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public Mono<MalaBarResponse<User>> getPersonById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/users-timeline/{id}")
    public Mono<MalaBarResponse<UserTimeLine>> getUserTimeLine(@PathVariable Long id){
        return userService.getUserTimeLine3(id);
    }


    @GetMapping("/users2")
    public Mono<MalaBarResponse<List<User>>> getUsers(@RequestParam Long from, @RequestParam Long to){
        return userService.fetchUsers(from,to);
    }
}
