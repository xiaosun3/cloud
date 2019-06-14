package com.cloud.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


/**
 * Created by sunhaidi on 2019-03-05.
 */
@Data
@Document(indexName = "goodsindex", type = "goods")
//indexName索引名称 可以理解为数据库名 必须为小写 不然会报org.con.cloud.elasticsearch.indices.InvalidIndexNameException异常
//type类型 可以理解为表名
public class GoodsInfo implements Serializable {

    private String id;
    private String name;
    private String description;

    public GoodsInfo() {
    }

    public GoodsInfo(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


}
