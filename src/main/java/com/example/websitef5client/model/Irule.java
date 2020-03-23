package com.example.websitef5client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author : jack lu
 * @date : 2020/2/25
 */
@Data
public class Irule {
    @Id
    String id;
    String kind;
    String selfLink;
    private List<item> items;
}
