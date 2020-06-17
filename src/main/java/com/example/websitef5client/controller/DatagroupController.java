package com.example.websitef5client.controller;

import com.cloume.commons.rest.response.RestResponse;
import com.example.websitef5client.model.DataGroup;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : jack lu
 * @date : 20200323
 */
@RestController
@RequestMapping(value = "/mgmt/tm/ltm")
public class DatagroupController {
    /**
     * 修改data_group
     *
     * @param name
     * @param body
     * @return
     */
    @PutMapping("/data-group/internal/{name}")
    public RestResponse<?> update_data_group(@PathVariable String name, @RequestBody Map<String, Object> body) {

        for (Map.Entry<String, Object> entry : body.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ",value=" + entry.getValue());
        }
        return RestResponse.good(null);
    }
}
