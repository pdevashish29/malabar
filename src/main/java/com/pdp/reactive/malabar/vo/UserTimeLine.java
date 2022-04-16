package com.pdp.reactive.malabar.vo;

import com.pdp.reactive.malabar.model.Post;
import com.pdp.reactive.malabar.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserTimeLine {

    private User user;
    private List<Post> posts;

}
