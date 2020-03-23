package com.example.websitef5client.service.impl;

import com.example.websitef5client.model.DataGroup;
import com.example.websitef5client.model.base.Paging;
import com.example.websitef5client.repository.DatagroupRepository;
import com.example.websitef5client.service.Idatagroup;
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
public class DatagroupImpl extends AbstractService implements Idatagroup {

    @Autowired
    DatagroupRepository idatagroupRepository;

    @Override
    public DataGroup create() {
        return new DataGroup();
    }

    @Override
    public MongoRepository<DataGroup, String> getRepository() {
        return idatagroupRepository;
    }

    @Override
    public Paging<List<DataGroup>> list(int page, int size) {
        Criteria criteria = new Criteria();
        return Paging.of(mongoTemplate.find(Query.query(criteria).with(new Sort(new Sort.Order(Sort.Direction.DESC, "createdTime")))
                        .with(new PageRequest(page, size)), DataGroup.class),
                mongoTemplate.count(Query.query(criteria), DataGroup.class));
    }
    @Override
    public DataGroup findByName(String name){
        return idatagroupRepository.findByName(name);
    }
}
