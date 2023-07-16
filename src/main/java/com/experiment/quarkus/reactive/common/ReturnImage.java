package com.experiment.quarkus.reactive.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ReturnImage implements Serializable{
    private String id;
    private String name;
    private String url;
    private boolean isSuccess;
}
