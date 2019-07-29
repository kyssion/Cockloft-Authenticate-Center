package com.cockloft.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDto implements Serializable {
    private String token;
    private String md5;
}
