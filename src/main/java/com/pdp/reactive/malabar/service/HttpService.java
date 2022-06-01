package com.pdp.reactive.malabar.service;

import com.pdp.reactive.malabar.model.Comment;
import com.pdp.reactive.malabar.model.Post;
import com.pdp.reactive.malabar.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class HttpService {


    @Autowired
    WebClient webClient;
    //https://jsonplaceholder.typicode.com/
   // jsonplaceholdeer base URL


    public Flux<User> getUsers(){
        log.info("HttpService getUsers ....");
       return webClient.get()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(User.class);
                    }
                    else {
                        log.error(response.statusCode()+"");
                        return response.createException().flatMapMany(Mono::error);
                    }
                });

    }

    public Mono<User> getUserById(Long id){
        return webClient.get().uri("/users/"+id)
                .retrieve()
                .bodyToMono(User.class);
    }
    public Flux<User> fetchUsers(List<Long> userIds) {
        return Flux.fromIterable(userIds)
                .flatMap(this::getUserById);
    }

    public Mono<List<Post>> getPostsByUserId(Long userId){
        return webClient.get().uri("/posts?userId="+userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Post>>() {});

    }

    public Flux<Comment> getCommentsByPostId(Long postId){
        return webClient.get().uri("/comments?postId="+postId)
                .retrieve()
                .bodyToFlux(Comment.class);
    }





}
