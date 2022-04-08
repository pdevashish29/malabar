package com.pdp.reactive.malabar.service;

import com.pdp.reactive.malabar.model.MalaBarResponse;
import com.pdp.reactive.malabar.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
@SpringBootTest
public class UserServiceTest {


    @InjectMocks
    private UserService userService;

    @Mock
    private HttpService httpService;


    @Test
    public void getUsersTest(){
        User user = new User();
        user.setName("Parashar");
        User user2 = new User();
        user2.setName("Devashish");
        Flux<User>  userFlux= Flux.just(user,user2);
        when(httpService.getUsers()).thenReturn(userFlux);

        Flux<User> users = userService.getUsers();
        StepVerifier.create(users).expectNext(user,user2).verifyComplete();
    }


    @Test
    public void getUserByIdTest(){
        User user = new User();
        user.setName("Parashar");
        MalaBarResponse<User> malaBarResponse = new MalaBarResponse<User>();
        malaBarResponse.setData(user);
        when(httpService.getUserById(Mockito.anyLong())).thenReturn(Mono.just(user));
        StepVerifier.create( userService.getUserById(1l)).expectNext(malaBarResponse).verifyComplete();
    }

    @Test
    public void fetchUsersTest(){
    }
}
