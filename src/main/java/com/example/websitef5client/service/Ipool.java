package com.example.websitef5client.service;

import com.example.websitef5client.model.Pool;
import com.example.websitef5client.model.base.Paging;
import com.example.websitef5client.service.base.IBaseService;

import java.util.List;

/**
 * @author : jack lu
 * @date :
 */
public interface Ipool extends IBaseService<Pool> {
    Paging<List<Pool>> list(int page, int size);
    Pool findByName(String name);
}
