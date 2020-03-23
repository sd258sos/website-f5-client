package com.example.websitef5client.service;

import com.example.websitef5client.model.DataGroup;
import com.example.websitef5client.model.Irule;
import com.example.websitef5client.model.base.Paging;
import com.example.websitef5client.service.base.IBaseService;

import java.util.List;

/**
 * @author : jack lu
 * @date :
 */
public interface Idatagroup extends IBaseService<DataGroup> {
    Paging<List<DataGroup>> list(int page, int size);
    DataGroup findByName(String name);
}
