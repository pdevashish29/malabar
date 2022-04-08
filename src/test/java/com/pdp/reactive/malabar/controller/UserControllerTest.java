package com.pdp.reactive.malabar.controller;

import com.pdp.reactive.malabar.model.MalaBarResponse;
import com.pdp.reactive.malabar.model.User;
import com.pdp.reactive.malabar.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @MockBean
    private UserService service;

    @Autowired
    private WebTestClient webClient;



    @Test
    public void getUsersTest() throws Exception {
        User user = new User();
        user.setId(101l);
        user.setName("Parashar");
        user.setAge(25);
        Flux<User> userFlux=Flux.just(user);
        when(service.getUsers()).thenReturn(userFlux);
        webClient.get().uri("/users")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(User.class);
        Mockito.verify(service, times(1)).getUsers();
    }

    @Test
    public void getUserByIdTest() throws Exception {
        User user = new User();
        user.setId(101l);
        user.setName("Parashar");
        user.setAge(25);
        MalaBarResponse<User> malaBarResponse= new MalaBarResponse<>();
        malaBarResponse.setData(user);
        when(service.getUserById(Mockito.anyLong())).thenReturn(Mono.just(malaBarResponse));

        webClient.get().uri("/users","1L")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(MalaBarResponse.class);
    }
}
