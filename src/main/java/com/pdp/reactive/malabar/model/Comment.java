package com.pdp.reactive.malabar.model;


import lombok.Data;

@SuppressWarnings("ALL")
@Data
public class Comment {

    private Long id;
    private Long postId;
    private String name;
    private  String email;
    private String body;


    }
