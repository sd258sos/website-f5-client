package com.example.websitef5client.service.impl;

import com.example.websitef5client.model.Irule;
import com.example.websitef5client.model.Pool;
import com.example.websitef5client.model.base.Paging;
import com.example.websitef5client.repository.PoolRespository;
import com.example.websitef5client.service.Ipool;
import com.example.websitef5client.service.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : jack lu
 * @date :
 */
@Service
public class PoolImpl extends AbstractService implements Ipool {
    @Autowired
    PoolRespository poolRespository;
    @Override
    public Paging<List<Pool>> list(int page, int size) {
        Criteria criteria = new Criteria();
        return Paging.of(mongoTemplate.find(Query.query(criteria).with(new Sort(new Sort.Order(Sort.Direction.DESC, "createdTime")))
                        .with(new PageRequest(page, size)), Pool.class),
                mongoTemplate.count(Query.query(criteria), Pool.class));
    }

    @Override
    public Pool create() {
        return new Pool();
    }

    @Override
    public MongoRepository<Pool, String> getRepository() {
        return poolRespository;
    }

    @Override
    public Pool findByName(String name){
        return poolRespository.findByName(name);
    }
}
