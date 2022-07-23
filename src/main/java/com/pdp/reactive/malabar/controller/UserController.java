package com.pdp.reactive.malabar.controller;

import com.pdp.reactive.malabar.model.CompanyVO;
import com.pdp.reactive.malabar.vo.MalaBarResponse;
import com.pdp.reactive.malabar.model.User;
import com.pdp.reactive.malabar.vo.UserTimeLine;
import com.pdp.reactive.malabar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
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

    @GetMapping("/search-users")
    public Flux<CompanyVO> searchUser() {
        log.info("UserController getUsers ....");
       //  List<User> users = userService.getUsers().collectList().block();
        //List<String> list = users.stream().filter(user -> user.getCompany().getName().startsWith("R")).map(item -> item.getCompany().getName()).collect(Collectors.toList());
        //System.out.println(list.size());
        //System.out.println(list);
      return userService.getUsers().filter(user -> user.getCompany().getName().startsWith("R"))
              .map(item -> {
                  CompanyVO companyVO = new CompanyVO();
                  companyVO.setName(item.getCompany().getName());
                  return companyVO;
              });

         //return Flux.empty();
    }

}
