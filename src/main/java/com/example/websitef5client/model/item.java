package com.example.websitef5client.model;

import lombok.Data;

import java.util.Map;

/**
 * @author : jack lu
 * @date : 20200323
 */
@Data
public class item {
    private String kind;
    private String name;
    private String partition;
    private String fullPath;
    private Integer generation;
    private String selfLink;
    private String apiAnonymous;
    private Map<String,String> apiRawValues;
}
