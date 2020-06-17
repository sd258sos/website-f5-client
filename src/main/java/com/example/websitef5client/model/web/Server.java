package com.example.websitef5client.model.web;

import lombok.Data;

/**
 * @author : jack lu
 * @date 2020/6/12
 * @Version: 1.0
 */
@Data
public class Server {
    private String ip;
    private String port;
    private String protocol;
    private String content;
}
