package com.app.demo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonResponse implements Serializable {

    private String statusCode;
    private String statusDescription;
    private Object payload;
}
