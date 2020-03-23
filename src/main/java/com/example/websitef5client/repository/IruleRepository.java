package com.example.websitef5client.repository;

import com.example.websitef5client.model.Irule;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : jack lu
 * @date :
 */
public interface IruleRepository extends MongoRepository<Irule,String > {
    Irule findBySelfLink(String selfLink);
}
