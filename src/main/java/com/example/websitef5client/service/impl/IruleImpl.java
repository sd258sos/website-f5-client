package com.example.websitef5client.service.impl;

import com.example.websitef5client.model.DataGroup;
import com.example.websitef5client.model.Irule;
import com.example.websitef5client.model.base.Paging;
import com.example.websitef5client.repository.IruleRepository;
import com.example.websitef5client.repository.PoolRespository;
import com.example.websitef5client.service.Iirule;
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
public class IruleImpl extends AbstractService implements Iirule {
    @Autowired
    IruleRepository iruleRepository;

    @Override
    public Paging<List<Irule>> list(int page, int size) {
        Criteria criteria = new Criteria();
        return Paging.of(mongoTemplate.find(Query.query(criteria).with(new Sort(new Sort.Order(Sort.Direction.DESC, "createdTime")))
                        .with(new PageRequest(page, size)), Irule.class),
                mongoTemplate.count(Query.query(criteria), Irule.class));
    }

    @Override
    public Irule create() {
        return new Irule();
    }

    @Override
    public MongoRepository<Irule, String> getRepository() {
        return iruleRepository;
    }

    @Override
    public Irule findByName(String name){
        return iruleRepository.findBySelfLink(name);
    }

}
