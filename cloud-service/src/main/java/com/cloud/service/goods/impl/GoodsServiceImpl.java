package com.cloud.service.goods.impl;

import com.cloud.jpa.entity.Goods;
import com.cloud.jpa.repository.GoodsRepository;
import com.cloud.service.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by sunhaidi on 2019-04-10.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsRepository goodsRepository;

    @Override
    @Transactional
    public void save(Goods goods) {
        try {
//            goodsRepository.save(goods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
