package com.pdp.reactive.malabar.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.reactive.malabar.vo.MalaBarResponse;
import com.pdp.reactive.malabar.model.User;
import com.pdp.reactive.malabar.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Type;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class UserControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ResourceLoader  resourceLoader;

    @Autowired
    private  ObjectMapper mapper;

    @Test
     void getUsersTest() throws Exception {
       List<User> users = mapper.readValue(resourceLoader.getResource("classpath:users.json").getFile(), new TypeReference<List<User>>() {});
        when(service.getUsers()).thenReturn( Flux.fromIterable(users));
        webClient.get().uri("/users")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class).hasSize(10);
    }

    @Test
     void getUserByIdTest() throws Exception {
       MalaBarResponse malabarResponse = mapper.readValue(resourceLoader.getResource("classpath:user.json").getFile(), MalaBarResponse.class);
       System.out.println(malabarResponse);
       when(service.getUserById(Mockito.anyLong())).thenReturn(Mono.just(malabarResponse));
        webClient.get().uri("/users","1L")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MalaBarResponse.class);
    }
}
