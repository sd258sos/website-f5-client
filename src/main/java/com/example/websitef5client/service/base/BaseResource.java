package com.example.websitef5client.service.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.query.Criteria;

@Data
@NoArgsConstructor
public abstract class BaseResource {

    @Id
    private String id;

    /**
     * 创建时间
     */
    private Long createdTime = System.currentTimeMillis();

    /**
     * 是否被移除
     */
    private Boolean isRemoved = false;

    /**
     * 创建人是admin还是user
     */
    private String creator;

    /**
     * 获取没有被移除的 Criteria
     *
     * @return
     */
    @JsonIgnore
    public static Criteria neRemoved() {
        return Criteria.where("isRemoved").ne(true);
    }
}
