package com.example.websitef5client.controller;

import com.cloume.commons.rest.response.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : jack lu
 * @date 2020/5/6
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "/mgmt/tm/ltm")
public class PoolController {
    /**
     * rule列表
     *
     * @return
     */
    @PostMapping("/pool")
    public RestResponse<?> postPool(@RequestBody Map<String, Object> body) {
        for(Map.Entry<String, Object> entry : body.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ",value=" + entry.getValue());
        }
        return RestResponse.good(null);
    }
    /**
     * rule列表
     *
     * @return
     */
    @PostMapping("/pool/~Common~/{id}/members?ver=13.1.1.2")
    public RestResponse<?> postIpMember(@RequestBody Map<String, Object> body) {
        for(Map.Entry<String, Object> entry : body.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ",value=" + entry.getValue());
        }
        return RestResponse.good(null);
    }
    @DeleteMapping("/pool/{id}")
    public RestResponse<?> deletePool(@PathVariable String id) {
            System.out.println("PathVariable:"+id);
        return RestResponse.good(null);
    }
    @PostMapping("/pool/{id}")
    public RestResponse<?> postPools(@RequestBody Map<String, Object> body) {
        for(Map.Entry<String, Object> entry : body.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ",value=" + entry.getValue());
        }
        return RestResponse.good(null);
    }
}
