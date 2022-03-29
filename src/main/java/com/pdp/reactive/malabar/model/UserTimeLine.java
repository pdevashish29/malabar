package com.pdp.reactive.malabar.model;

import lombok.Data;

import java.util.List;

@Data
public class UserTimeLine {

    private  User user;
    private List<Post> posts;

}
