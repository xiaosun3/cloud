package com.cloud.controller.index;

import com.cloud.service.goods.GoodsInfoService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    @Autowired
    GoodsInfoService goodsService;

    @RequestMapping(path = "/isnsert", method = RequestMethod.GET)
    public Object insert() {
        try {
            Thread.sleep(RandomUtils.nextInt(500,1500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }


}
