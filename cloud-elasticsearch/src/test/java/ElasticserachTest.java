import com.alibaba.fastjson.JSON;
import com.cloud.configuration.Application;
import com.cloud.entity.GoodsInfo;
import com.cloud.repository.GoodsInfoRepository;
import com.google.common.collect.Lists;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.elasticsearch.index.query.QueryBuilders.spanTermQuery;

/**
 * Created by sunhaidi on 2019-03-05.
 * bool     组合查询
 * match    会分词匹配
 * term     不会分词匹配
 * must     必须满足条件
 * should   可以满足可以不满足
 * must_not 不满足条件
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("de")
public class ElasticserachTest {

    @Autowired
    private GoodsInfoRepository goodsInfoRepository;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 新增索引
     */
    @Test
    public void saveIndex() {
        GoodsInfo goodsInfo4 = new GoodsInfo("4","洋河梦之蓝M4", "河蓝色经典 梦之蓝M4 45度 礼盒装500ml*2瓶白酒 口感绵柔浓香型1");
        List l = Lists.newArrayList(goodsInfo4);

//        goodsInfoRepository.saveAll(l);

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withIndexName("goodsindex")
                .withType("goods")
                .withId(goodsInfo4.getId())
                .withObject(goodsInfo4) //对象或集合
                .build();
        elasticsearchTemplate.index(indexQuery);
    }

    /**
     * 获取索引 match
     */
    @Test
    public void queryByMatchQuery() {
     String serchStr = "ml";
     QueryBuilder queryBuilder = QueryBuilders.matchQuery("description", serchStr);

     SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

     List<GoodsInfo> goodsInfos = elasticsearchTemplate.queryForList(searchQuery, GoodsInfo.class);
     for (GoodsInfo goodsInfo : goodsInfos) {
     System.out.println(goodsInfo.toString());
     }
     }

     /**
     * 获取索引 bool
     */
    @Test
    public void queryByboolQuery() {
        String serchStr = "ml";
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.should(QueryBuilders.matchQuery("name", serchStr));
        queryBuilder.should(QueryBuilders.matchQuery("description", serchStr));

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

        List<GoodsInfo> goodsInfos = elasticsearchTemplate.queryForList(searchQuery, GoodsInfo.class);
        for (GoodsInfo goodsInfo : goodsInfos) {
            System.out.println(goodsInfo.toString());
        }
    }

    @Test
    public void query() {
        SortBuilder sortBuilder = SortBuilders.fieldSort("price")       //排序字段
                                              .order(SortOrder.DESC);   //排序方式
        Pageable pageable = PageRequest.of(0, 10);//查看第0页，以每页10条划分

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery()) // 自定义查询
                .withSort(sortBuilder)
                .withPageable(pageable) // 自定义分页
                .build();

        Page<GoodsInfo> page = elasticsearchTemplate.queryForPage(searchQuery, GoodsInfo.class);
        String str = String.format("页数:%s 行数:%s 大小:%s 当前第几页:%s 当前页的数量:%s",
                    page.getTotalPages(),
                    page.getTotalElements(),
                    page.getSize(),
                    page.getNumber(),
                    page.getNumberOfElements() );

        System.out.println(str);
        for (GoodsInfo goodsInfo : page.getContent()) {
            System.out.println(JSON.toJSON(goodsInfo));
        }

    }

    /**
     * 指定 analyzer 获取字符串分词结果
     */
    @Test
    public void getanalyze() {
        String content = "洋河梦之蓝M1";
        // 调用 IK 分词分词
        AnalyzeRequestBuilder analyzeRequest = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(),
                AnalyzeAction.INSTANCE,"goodsindex", content);
        analyzeRequest.setAnalyzer("ik_smart");
        List<AnalyzeResponse.AnalyzeToken> ikTokenList = analyzeRequest.execute().actionGet().getTokens();

        // 循环赋值标准
        List<String> searchTermList = new ArrayList<>();
        ikTokenList.forEach(ikToken -> {
            searchTermList.add(ikToken.getTerm());
        });
        for (String s : searchTermList) {
            System.out.println(s);
        }

    }

    /**
     * 更新索引
     */
    @Test
    public void updateIndex() {
        GoodsInfo goodsInfo4 = new GoodsInfo("1",
                "洋河梦之蓝M1 ml", "description1");
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source("name", "洋河梦之蓝M1 ml");
        UpdateQuery indexQuery = new UpdateQueryBuilder()
//                .withIndexName("goodsindex")
//                .withType("goods")
                .withId(goodsInfo4.getId())
                .withClass(GoodsInfo.class)
                .withIndexRequest(indexRequest)
                .build();
        elasticsearchTemplate.update(indexQuery);
    }

    /**
     * 删除索引
     */
    @Test
    public void deleteIndex() {
        //第一种删除具体的一条记录
        elasticsearchTemplate.delete("goodsindex","goods","3");

        //第二种删除indexName/type/下的所有
        DeleteQuery deleteQuery = new DeleteQuery();
        deleteQuery.setIndex("goodsindex");
        deleteQuery.setType("goods");
        elasticsearchTemplate.delete(deleteQuery);

        //第三种删除indexName/下的所有
        elasticsearchTemplate.deleteIndex("goodsindex");

        //第四种删除查询出来的所有
        deleteQuery = new DeleteQuery();
        deleteQuery.setQuery(QueryBuilders.matchQuery("id","100"));
        elasticsearchTemplate.delete(deleteQuery);
    }

    /**
     * 获取索引 mapping
     */
    @Test
    public void getMapping() {
        Map map =  elasticsearchTemplate.getMapping("goodsindex","goods");
        System.out.println(JSON.toJSON(map));
    }


}
