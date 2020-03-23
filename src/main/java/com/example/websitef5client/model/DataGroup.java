package com.example.websitef5client.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * @author : jack lu
 * @date : 2020/2/25
 */
@Data
public class DataGroup {
    @Id
    String id;
    String name;
    List<Record> records;
}
