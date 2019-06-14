package com.cloud.service.goods.impl;

import com.cloud.repository.GoodsInfoRepository;
import com.cloud.service.goods.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sunhaidi on 2019-04-10.
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    GoodsInfoRepository goodsRepository;


}
