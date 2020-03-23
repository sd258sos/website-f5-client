package com.example.websitef5client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author : jack lu
 * @date : 20200323
 */
@Data
public class Data_group {
    @Id
    private String id;
    private String kind;
    private String name;
    private String fullPath;
    private Integer generation;
    private String selfLink;
    private String type;
    List<Record> records;
}
