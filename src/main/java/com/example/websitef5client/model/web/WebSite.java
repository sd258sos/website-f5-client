package com.example.websitef5client.model.web;

import lombok.Data;

import java.util.List;

/**
 * @author : jack lu
 * @date 2020/6/12
 * @Version: 1.0
 */
@Data
public class WebSite {
    private String name;
    private String domain;
    private List<Server> serverList;
    private String status;
    private String dns;
    private String access;
    private boolean execution;
    private String address;
}
