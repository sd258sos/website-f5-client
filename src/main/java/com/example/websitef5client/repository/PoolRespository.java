package com.example.websitef5client.repository;

import com.example.websitef5client.model.Pool;
import com.example.websitef5client.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : jack lu
 * @date :
 */
public interface PoolRespository extends MongoRepository<Pool,String > {
    Pool findByName(String name);
}
