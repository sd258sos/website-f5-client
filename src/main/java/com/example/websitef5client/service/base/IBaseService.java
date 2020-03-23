package com.example.websitef5client.service.base;

import com.cloume.commons.beanutils.IConverter;
import com.cloume.commons.beanutils.Updater;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;

/**
 * @author He
 */
public interface IBaseService<T> {

    /**
     * 创建新的T
     *
     * @return
     */
    T create();

    /**
     * 返回与Service对应的Repository
     *
     * @return
     */
    MongoRepository<T, String> getRepository();

    /**
     * 更新
     *
     * @param t
     * @return
     */
    default T wrap(T t, Map<String, Object> map) {
        return wrap(t, map, null);
    }

    /**
     * 更新
     *
     * @param t
     * @param map
     * @param converter
     * @return
     */
    default T wrap(T t, Map<String, Object> map, IConverter converter) {
        if (map == null) {
            return null;
        }
        return Updater.wrap(t).update(map, converter);
    }

    /**
     * 保存
     *
     * @param map
     * @return
     */
    default T save(Map<String, Object> map) {
        T t = this.wrap(this.create(), map);
        if (t == null) {
            return null;
        }
        return getRepository().save(t);
    }

    /**
     * 保存
     *
     * @param t
     */
    default T save(T t) {
        if (t == null) {
            return null;
        }
        return getRepository().save(t);
    }

    /**
     * 更新
     *
     * @param t
     * @param map
     * @return
     */
    default T update(T t, Map<String, Object> map) {
        if (t == null) {
            return t;
        }
        return getRepository().save(this.wrap(t, map));
    }

    /**
     * 删除
     *
     * @param t
     */
    default T delete(T t) {
        if (t == null) {
            return null;
        }
        getRepository().delete(t);
        return t;
    }

    /**
     * 默认按照 id 降序排列
     *
     * @return
     */
    default Sort defaultSort() {
        return new Sort(Sort.Direction.DESC, "_id");
    }

    /**
     * 根据主键ID进行查找
     *
     * @param id
     * @return
     */
    default T find(String id) {
        try {
            return getRepository().findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据id列表批量删除
     *
     * @param ids
     */
    default void batchDelete(List<String> ids) {
        if (ids == null) {
            return;
        }

        ids.stream().forEach(t -> delete(find(t)));
    }

}