package com.pdp.reactive.malabar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private  Integer age;
    private String email;
    private Address address;
    private String phone;
    private  String website;
    private  Company company;


}
