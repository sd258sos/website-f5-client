package com.example.websitef5client.service;

import com.example.websitef5client.model.Irule;
import com.example.websitef5client.model.Pool;
import com.example.websitef5client.model.base.Paging;
import com.example.websitef5client.service.base.IBaseService;

import java.util.List;

/**
 * @author : jack lu
 * @date :
 */
public interface Iirule extends IBaseService<Irule> {
    Paging<List<Irule>> list(int page, int size);
    Irule findByName(String name);
}
