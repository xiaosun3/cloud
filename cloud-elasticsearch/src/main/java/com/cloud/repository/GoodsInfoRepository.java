package com.cloud.repository;

import com.cloud.entity.GoodsInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsInfoRepository extends ElasticsearchRepository<GoodsInfo,String> {


}
