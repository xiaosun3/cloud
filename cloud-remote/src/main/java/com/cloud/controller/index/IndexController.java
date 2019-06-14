package com.cloud.controller.index;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cloud.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@RestController
@RequestMapping(value = "/api")
public class IndexController {

    @Autowired
    GoodsService goodsService;

    @NacosValue(value = "${username:abc}", autoRefreshed = true)
    private String username;

    @RequestMapping(path = "nacos", method = RequestMethod.GET)
    public Object index() {
        System.out.println("remote index success");
        return username;
    }

}
