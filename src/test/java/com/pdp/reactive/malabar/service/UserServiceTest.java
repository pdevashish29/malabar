package com.pdp.reactive.malabar.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.reactive.malabar.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private  UserServiceImpl userService;

    @Mock
    private HttpService httpService;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ObjectMapper mapper;



    @Test
    void getUsersTest() throws Exception {
        List<User> users = mapper.readValue(resourceLoader.getResource("classpath:users.json").getFile(), new TypeReference<List<User>>() {});
        Mockito.when(httpService.getUsers()).thenReturn(Flux.fromIterable(users));
        StepVerifier.create(userService.getUsers()).expectSubscription().expectNextCount(10).expectComplete();

    }


}
