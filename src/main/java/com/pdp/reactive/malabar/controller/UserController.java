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

@SuppressWarnings("ALL")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Flux<User> getPerson(){
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public Mono<MalaBarResponse<User>> getPerson(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/users-timeline/{id}")
    public Mono<MalaBarResponse<UserTimeLine>> getUserTimeLine(@PathVariable Long id){
        log.info("users-timeline/"+id);
       //return userService.getUserTimeLine(id);
        //return userService.getUserTimeLine2(id);
        // user  Call --1 user
        // List<Post> call  -10 POST  -- 1post == 5 comments
         //       Post --> List<Comment> 50 Comments
        return userService.getUserTimeLine3(id);
    }


    @GetMapping("/users2")
    public Mono<MalaBarResponse<List<User>>> getUsers(@RequestParam Long from, @RequestParam Long to){
        return userService.fetchUsers(from,to);
    }
}
