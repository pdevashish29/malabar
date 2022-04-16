package com.pdp.reactive.malabar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class ProductController {

    @GetMapping("/products")
    public Mono<String> hello(){
        log.info("UserController index ....");
        return Mono.just("product is empty 01");
    }
}
