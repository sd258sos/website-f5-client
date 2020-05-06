package com.example.websitef5client.controller;

import com.cloume.commons.rest.response.PagingRestResponse;
import com.cloume.commons.rest.response.RestResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : jack lu
 * @date 2020/5/6
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/api/v1/servers/localhost")
public class DNSController {
    @PatchMapping("/zones/{id}")
    public RestResponse dns_list(@PathVariable String id,@RequestBody Map<String, Object> body){
        for(Map.Entry<String, Object> entry : body.entrySet()) {
            System.out.print("Key = " + entry.getKey() + ",value=" + entry.getValue());
        }
        return RestResponse.good(null);
    }

}
