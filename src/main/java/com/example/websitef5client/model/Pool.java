package com.example.websitef5client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author : jack lu
 * @date : 2020/2/25
 */
@Data
public class Pool {
    @Id
    String id;
    String name;
    String ip;
}
