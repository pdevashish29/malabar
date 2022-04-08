package com.pdp.reactive.malabar.model;

import lombok.Data;

@Data
public class Address {

    private String street;
    private String  suite;
    private String city;
    private  String zipcode;
    private Geo geo;


}
