package com.pdp.reactive.malabar.vo;

import lombok.Data;

import java.util.List;

@Data
public class MalaBarResponse<T> {

    T data;
    List<Details> errors;
}
