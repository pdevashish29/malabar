//package com.pdp.reactive.malabar.controller;
//
//import com.pdp.reactive.malabar.model.User;
//import com.pdp.reactive.malabar.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import reactor.core.publisher.Flux;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//
//@SpringBootTest
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService service;
//
//    @Test
//    public void getPersonsTest() throws Exception {
//        User user = new User();
//        user.setId(101l);
//        user.setName("Parashar");
//        user.setAge(25);
//        Flux<User> userFlux=Flux.just(user);
//        when(service.getUsers()).thenReturn(userFlux);
//        mockMvc.perform(get("users")).andExpect(status().isOk());
//
//    }
//}
