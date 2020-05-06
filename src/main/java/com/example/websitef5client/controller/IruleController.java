package com.example.websitef5client.controller;

import com.alibaba.fastjson.JSON;
import com.cloume.commons.rest.response.PagingRestResponse;
import com.cloume.commons.rest.response.RestResponse;
import com.example.websitef5client.model.Irule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author : jack lu
 * @date : 20200323
 */
@RestController
@RequestMapping(value = "/mgmt/tm/ltm")
public class IruleController {
    @Value("${initialzation.file.irule:irule.json}")
    private String iruleFile;


    /**
     * rule列表
     *
     * @return
     */
    @GetMapping("/rule")
    public RestResponse<Irule> list() {
        Irule status = null;
        InputStream subsidyInputStream = getClass().getClassLoader().getResourceAsStream(iruleFile);

        if(subsidyInputStream == null){
            try {
                throw new Exception("initialzation Setting file not found: " + iruleFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {

            try {
                status = JSON.parseObject(subsidyInputStream, Irule.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return PagingRestResponse.good(status);
    }

    /**
     * rule列表
     *
     * @return
     */
    @PostMapping("/rule")
    public RestResponse<?> postRule(@RequestBody Map<String, Object> body) {
        for(Map.Entry<String, Object> entry : body.entrySet()) {
            System.out.print("Key = " + entry.getKey() + ",value=" + entry.getValue());
        }
        return RestResponse.good(null);
    }

}
