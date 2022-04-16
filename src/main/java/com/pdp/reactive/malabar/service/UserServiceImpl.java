package com.pdp.reactive.malabar.service;

import com.pdp.reactive.malabar.model.*;
import com.pdp.reactive.malabar.vo.MalaBarResponse;
import com.pdp.reactive.malabar.vo.UserTimeLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private HttpService httpService;

    @Override
    public Flux<User> getUsers() {
        log.info("UserServiceImpl getUsers ....");
        return httpService.getUsers();
    }

    @Override
    public Mono<MalaBarResponse<User>> getUserById(Long id) {
        log.info("UserServiceImpl getUserById ....",id);
        return httpService.getUserById(id).flatMap(item -> {
            MalaBarResponse<User> response = new MalaBarResponse<>();
            response.setData(item);
            return Mono.just(response);
        });
    }



    @Override
    public Mono<MalaBarResponse<UserTimeLine>> getUserTimeLine3(Long userId) {
        log.info("UserServiceImpl getUserTimeLine3 ....",userId);
        Mono<User> user = httpService.getUserById(userId);
        Mono<List<Post>> posts = httpService.getPostsByUserId(userId).flatMap(this::populateComments);
        return Mono.zip(user,posts).flatMap(this::populateResponse);
    }


    private Mono<List<Post>> populateComments(List<Post> item1) {
        log.info("UserServiceImpl populateComments ....");
         return Flux.fromIterable(item1).flatMap(post -> httpService.getCommentsByPostId(post.getId()).collectSortedList(Comparator.comparing(Comment::getId)).flatMap(item2 -> {
            post.setComments(item2);
            return Mono.just(post);
        })).collectSortedList(Comparator.comparing(Post::getUserId)).flatMap(Mono::just);
    }

    private  Mono<MalaBarResponse<UserTimeLine>> populateResponse(Tuple2<User, List<Post>> data) {
        log.info("UserServiceImpl populateResponse ....");
        MalaBarResponse<UserTimeLine> response = new MalaBarResponse<>();
        UserTimeLine userTimeLine = new UserTimeLine();
        userTimeLine.setUser(data.getT1());
        userTimeLine.setPosts(data.getT2());
        response.setData(userTimeLine);
        return Mono.just(response);
    }
}