package com.example.websitef5client.repository;

import com.example.websitef5client.model.DataGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : jack lu
 * @date :
 */
public interface DatagroupRepository extends MongoRepository <DataGroup,String >{
    DataGroup findByName(String name);
}
