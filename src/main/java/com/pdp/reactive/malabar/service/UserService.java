package com.pdp.reactive.malabar.service;

import com.pdp.reactive.malabar.vo.MalaBarResponse;
import com.pdp.reactive.malabar.model.User;
import com.pdp.reactive.malabar.vo.UserTimeLine;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> getUsers();
    Mono<MalaBarResponse<User>> getUserById(Long id);
    Mono<MalaBarResponse<UserTimeLine>> getUserTimeLine3(Long userId);
}
