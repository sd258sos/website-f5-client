package com.example.websitef5client.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * @author He
 */
public abstract class AbstractService {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    protected GridFsTemplate gridFsTemplate;
}
