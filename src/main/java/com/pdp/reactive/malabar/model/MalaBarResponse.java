package com.pdp.reactive.malabar.model;

import lombok.Data;

import java.util.List;

@Data
public class MalaBarResponse<T> {

    T data;
    List<Details> errors;
}
