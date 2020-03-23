package com.example.websitef5client.model.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用于处理分页
 *
 * @param <T>
 * @author He
 */
@Data
@NoArgsConstructor
public class Paging<T> implements Serializable {

    public static <T> Paging of(T data, long count) {
        return new Paging(data, count);
    }

    private Paging(T data, long count) {
        this.data = data;
        this.count = count;
    }

    private Long count;

    private T data;
}
