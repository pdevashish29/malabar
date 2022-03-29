package com.pdp.reactive.malabar.service;

import com.pdp.reactive.malabar.model.MalaBarResponse;
import com.pdp.reactive.malabar.model.Post;
import com.pdp.reactive.malabar.model.User;
import com.pdp.reactive.malabar.model.UserTimeLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@SuppressWarnings("unused")
@Service
@Slf4j
public class UserService {

    @Autowired
    private HttpService httpService;

    public Flux<User> getUsers() {
        return httpService.getUsers();
    }

    public Mono<MalaBarResponse<User>> getUserById(Long id) {
        return httpService.getUserById(id).flatMap(item -> {
            MalaBarResponse<User> response = new MalaBarResponse<>();
            response.setData(item);
            return Mono.just(response);
        });
    }

    public Mono<MalaBarResponse<List<User>>> fetchUsers(Long fromUserId,Long toUserId) {
        MalaBarResponse<List<User>> response = new MalaBarResponse<>();
        List<Long> userIds = LongStream.rangeClosed(fromUserId, toUserId).boxed().collect(Collectors.toList());
        return httpService.fetchUsers(userIds)
                .collectSortedList(Comparator.comparing(o1 -> o1.getId())).flatMap(item -> {
            response.setData(item);
            return Mono.just(response);
        });

    }
    public Mono<MalaBarResponse<UserTimeLine>> getUserTimeLine(Long userId) {
        return httpService.getUserById(userId).flatMap(user -> {
           return httpService.getPostsByUserId(user.getId()).flatMap( posts -> {
                MalaBarResponse<UserTimeLine> response = new MalaBarResponse<>();
                UserTimeLine userTimeLine = new UserTimeLine();
                userTimeLine.setUser(user);
                userTimeLine.setPosts(posts);
                response.setData(userTimeLine);
               return Mono.just(response);
            });
        });
    }

    public Mono<MalaBarResponse<UserTimeLine>> getUserTimeLine2(Long userId) {
        MalaBarResponse<UserTimeLine> response = new MalaBarResponse<>();
        Mono<User> user = httpService.getUserById(userId);
        Mono<List<Post>> post = httpService.getPostsByUserId(userId);
       return Mono.zip(user,post).flatMap(this::populateResponse);
    }

    public Mono<MalaBarResponse<UserTimeLine>> getUserTimeLine3(Long userId) {
        Mono<User> user = httpService.getUserById(userId);
        Mono<List<Post>> posts = httpService.getPostsByUserId(userId).flatMap(this::populateComments);
        return Mono.zip(user,posts).flatMap(this::populateResponse);
    }

    private Mono<List<Post>> populateComments(List<Post> item1) {
        return Flux.fromIterable(item1).flatMap(post -> {
            return httpService.getCommentsByPostId(post.getId()).collectSortedList(Comparator.comparing(o1 -> o1.getId())).flatMap(item2 -> {
                post.setComments(item2);
                return Mono.just(post);
            });
        }).collectSortedList(Comparator.comparing(o1 -> o1.getId())).flatMap(data -> {
            return Mono.just(data);
        });
    }

    private  Mono<MalaBarResponse<UserTimeLine>> populateResponse(Tuple2<User, List<Post>> data) {
        MalaBarResponse<UserTimeLine> response = new MalaBarResponse<>();
        UserTimeLine userTimeLine = new UserTimeLine();
        userTimeLine.setUser(data.getT1());
        userTimeLine.setPosts(data.getT2());
        response.setData(userTimeLine);
        return Mono.just(response);
    }
}