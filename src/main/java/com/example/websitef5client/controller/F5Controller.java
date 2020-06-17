package com.example.websitef5client.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloume.commons.rest.response.PagingRestResponse;
import com.cloume.commons.rest.response.RestResponse;
import com.cloume.commons.verify.IRule;
import com.cloume.commons.verify.Verifier;
import com.example.websitef5client.model.DataGroup;
import com.example.websitef5client.model.Irule;
import com.example.websitef5client.model.Pool;
import com.example.websitef5client.model.base.Paging;
import com.example.websitef5client.model.web.Server;
import com.example.websitef5client.model.web.WebSite;
import com.example.websitef5client.service.Idatagroup;
import com.example.websitef5client.service.Iirule;
import com.example.websitef5client.service.Ipool;
import com.example.websitef5client.util.SignUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : jack lu
 * @date : 2020/2/25
 */
@RestController
@RequestMapping(value = "/test")
public class F5Controller {
    @Autowired
    Idatagroup idatagroup;
    @Autowired
    Iirule iirule;
    @Autowired
    Ipool ipool;


    /**
     *
     * host list  list
     * @param page 获取的第几页
     * @param size  一页的数据条数
     * @return
     */
    @GetMapping("/data-group/internal")
    public PagingRestResponse<?> host_list_list(@RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "1000") int size) {

        Paging<List<DataGroup>> paging = idatagroup.list(page, size);
        if (paging.getCount() == null || paging.getData() == null) {
            return PagingRestResponse.result(-2, "query failed", null);
        }
        Paging<List<DataGroup>> result = Paging.of(null, 0);
        List<DataGroup> dataGroups = new ArrayList<>();
        for (DataGroup dataGroup : paging.getData()) {
            dataGroups.add(dataGroup);
        }
        result.setData(dataGroups);
        return PagingRestResponse.good(result.getData(), paging.getCount());
    }

    /**
     * 根据name查询data_group详情
     *
     * @param name
     * @return
     */
    @ApiOperation("get device detail information")
    @GetMapping("/data-group/internal/{name}")
    public RestResponse<?> getDetailById_data_group(@PathVariable String name) {
        if (idatagroup == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }

        DataGroup dataGroup = idatagroup.findByName(name);
        return RestResponse.good(dataGroup);
    }

    /**
     * 创建 host_list
     * @return
     */
    @PostMapping("/data-group/internal")
    public RestResponse<?> host_list_create(@RequestBody Map<String, Object> body)  {
        if (!new Verifier()
                .rule("name")
                .result((r, s) -> {
                }).verify(body)
        ) {
            return RestResponse.bad(-1, "invalid request body");
        }
        DataGroup dataGroup = idatagroup.save(body);
        return RestResponse.good(dataGroup);
    }

    /**
     * 修改data_group
     *
     * @param name
     * @param body
     * @return
     */
    @PutMapping("/data-group/internal/{name}")
    public RestResponse<?> update_data_group(@PathVariable String name, @RequestBody Map<String, Object> body) {

        if (idatagroup == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }
        DataGroup dataGroup = idatagroup.findByName(name);
        if (dataGroup == null){
            return RestResponse.bad(-1, "device is not found by:" + name);
        }
        idatagroup.update(idatagroup.getRepository().findById(dataGroup.getId()).get(),body);
        return RestResponse.good(body);
    }

    /**
     * 通过名字删除data_group
     * @param name
     * @return
     */
    @DeleteMapping("/data-group/internal/{name}")
    public RestResponse<?> delete_data_group(@PathVariable String name){
        if (idatagroup == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }
        DataGroup dataGroup = idatagroup.findByName(name);
        if (dataGroup == null){
            return RestResponse.bad(-1, "device is not found by:" + name);
        }
        idatagroup.delete(idatagroup.getRepository().findById(dataGroup.getId()).get());
        return RestResponse.good(dataGroup);
    }

    /**
     *
     * rule list
     * @param page 获取的第几页
     * @param size  一页的数据条数
     * @return
     */
    @GetMapping("/rule")
    public PagingRestResponse<?> irule_list(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "1000") int size) {

        Paging<List<Irule>> paging = iirule.list(page, size);
        if (paging.getCount() == null || paging.getData() == null) {
            return PagingRestResponse.result(-2, "query failed", null);
        }
        Paging<List<Irule>> result = Paging.of(null, 0);
        List<Irule> irules = new ArrayList<>();
        for (Irule Irule : paging.getData()) {
            irules.add(Irule);
        }
        result.setData(irules);
        return PagingRestResponse.good(result.getData(), paging.getCount());
    }
    /**
     * 根据name查询data_group详情
     *
     * @param name
     * @return
     */
    @GetMapping("/rule/{name}")
    public RestResponse<?> getDetailByName_rule(@PathVariable String name) {
        if (iirule == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }

        Irule iRule = iirule.findByName(name);
        return RestResponse.good(iRule);
    }
    /**
     * 创建rule
     * @return
     */
    @PostMapping("/rule")
    public RestResponse<?> irule_create(@RequestBody Map<String, Object> body)  {
        if (!new Verifier()
                .rule("name")
                .rule("apiAnonymous")
                .result((r, s) -> {
                }).verify(body)
        ) {
            return RestResponse.bad(-1, "invalid request body");
        }
        Irule irule = iirule.save(body);
        return RestResponse.good(irule);
    }

    /**
     * 修改irule
     *
     * @param name
     * @param body
     * @return
     */
    @PutMapping("/rule/{name}")
    public RestResponse<?> update_rule(@PathVariable String name, @RequestBody Map<String, Object> body) {

        if (iirule == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }
        Irule irule = iirule.findByName(name);
        if (irule == null){
            return RestResponse.bad(-1, "device is not found by:" + name);
        }
        iirule.update(iirule.getRepository().findById(irule.getId()).get(),body);
        return RestResponse.good(body);
    }

    /**
     * 通过name删除rule
     * @param name
     * @return
     */
    @DeleteMapping("/rule/{name}")
    public RestResponse<?> delete_rule(@PathVariable String name){
        if (iirule == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }
        Irule irule = iirule.findByName(name);
        if (irule == null){
            return RestResponse.bad(-1, "device is not found by:" + name);
        }
        iirule.delete(iirule.getRepository().findById(irule.getId()).get());
        return RestResponse.good(irule);
    }
    /**
     *
     * pool list
     * @param page 获取的第几页
     * @param size  一页的数据条数
     * @return
     */
    @GetMapping("/pool")
    public PagingRestResponse<?> pool_list(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "1000") int size) {

        Paging<List<Pool>> paging = ipool.list(page, size);
        if (paging.getCount() == null || paging.getData() == null) {
            return PagingRestResponse.result(-2, "query failed", null);
        }
        Paging<List<Pool>> result = Paging.of(null, 0);
        List<Pool> pools = new ArrayList<>();
        for (Pool pool : paging.getData()) {
            pools.add(pool);
        }
        result.setData(pools);
        return PagingRestResponse.good(result.getData(), paging.getCount());
    }
    /**
     * 根据name查询pool详情
     *
     * @param name
     * @return
     */
    @GetMapping("/pool/{name}")
    public RestResponse<?> getDetailByName(@PathVariable String name) {
        if (ipool == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }
        Pool pool = ipool.findByName(name);
        return RestResponse.good(pool);
    }
    /**
     * 创建pool
     * @return
     */
    @PostMapping("/pool")
    public RestResponse<?> pool_create(@RequestBody Map<String, Object> body)  {
        if (!new Verifier()
                .rule("name")
                .result((r, s) -> {
                }).verify(body)
        ) {
            return RestResponse.bad(-1, "invalid request body");
        }
        Pool pool = ipool.save(body);
        return RestResponse.good(pool);
    }
    /**
     * 修改irule
     *
     * @param name
     * @param body
     * @return
     */
    @PutMapping("/pool/{name}")
    public RestResponse<?> update_pool(@PathVariable String name, @RequestBody Map<String, Object> body) {

        if (ipool == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }
        Pool pool = ipool.findByName(name);
        if (pool == null){
            return RestResponse.bad(-1, "device is not found by:" + name);
        }
        ipool.update(ipool.getRepository().findById(pool.getId()).get(),body);
        return RestResponse.good(body);
    }

    /**
     * 通过name删除pool
     * @param name
     * @return
     */
    @DeleteMapping("/pool/{name}")
    public RestResponse<?> delete_pool(@PathVariable String name){
        if (ipool == null) {
            return RestResponse.bad(-1, "device is not found by id:", name);
        }
        Pool pool = ipool.findByName(name);
        if (pool == null){
            return RestResponse.bad(-1, "device is not found by:" + name);
        }
        ipool.delete(ipool.getRepository().findById(pool.getId()).get());
        return RestResponse.good(pool);
    }


    @GetMapping("/test")
    public void test(){

        long systemCurrentMillis = System.currentTimeMillis();
        Map<String, Object> map =new HashMap<>();
        map.put("timeStamp",systemCurrentMillis);
        map.put("accessKey","ecnu");

        String result = null;

        JSONObject jsonObject = JSONUtil.createObj();

        WebSite webSite = new WebSite();
        webSite.setName("webTest");
        webSite.setDomain("test.ecnu.edu.cn");
        webSite.setStatus("1");
        webSite.setExecution(false);
        webSite.setAddress("psy.ecnu.edu.cn");
        webSite.setDns("A");
        List<Server> serverList = new ArrayList<>();
        Server server1 = new Server();
        server1.setIp("192.168.9.2");
        server1.setPort("80");
        server1.setProtocol("http");
        server1.setContent("备注1");
        serverList.add(server1);
        Server server2 = new Server();
        server2.setIp("192.168.9.2");
        server2.setPort("80");
        server2.setProtocol("http");
        server2.setContent("备注1");
        serverList.add(server2);
        webSite.setServerList(serverList);
        jsonObject = JSONUtil.parseObj(webSite, false);
        String bodyJson = JSONUtil.toJsonStr(jsonObject);

        try {
            result= HttpRequest.post("http://127.0.0.1:8089/web/addWebsite").
                    form("timeStamp",systemCurrentMillis).
                    form("accessKey","ecnu").
                    form("sign", SignUtil.createSign(map,"ecnu")).
//                    body(bodyJson).
                    timeout(20000).execute().body();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
