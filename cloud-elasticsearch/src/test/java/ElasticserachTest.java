import com.alibaba.fastjson.JSON;
import com.cloud.configuration.Application;
import com.cloud.entity.GoodsInfo;
import com.cloud.repository.GoodsInfoRepository;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Map;

/**
 * Created by sunhaidi on 2019-03-05.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("de")
public class ElasticserachTest {

    @Autowired
    private GoodsInfoRepository goodsInfoRepository;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void saveIndex() {
        GoodsInfo goodsInfo1 = new GoodsInfo("1",
                "洋河梦之蓝M1", "河蓝色经典 梦之蓝M1 45度 礼盒装500ml*2瓶白酒 口感绵柔浓香型");
        GoodsInfo goodsInfo2 = new GoodsInfo("2",
                "洋河梦之蓝M2", "河蓝色经典 梦之蓝M2 45度 礼盒装500ml*2瓶白酒 口感绵柔浓香型");
        GoodsInfo goodsInfo3 = new GoodsInfo("3",
                "洋河梦之蓝M3", "河蓝色经典 梦之蓝M3 45度 礼盒装500ml*2瓶白酒 口感绵柔浓香型");
        GoodsInfo goodsInfo4 = new GoodsInfo("4",
                "洋河梦之蓝M4", "河蓝色经典 梦之蓝M4 45度 礼盒装500ml*2瓶白酒 口感绵柔浓香型");
        GoodsInfo goodsInfo5 = new GoodsInfo("5",
                "洋河梦之蓝M5", "河蓝色经典 梦之蓝M5 45度 礼盒装500ml*2瓶白酒 口感绵柔浓香型");
        GoodsInfo goodsInfo6 = new GoodsInfo("6",
                "洋河梦之蓝M6", "河蓝色经典 梦之蓝M6 45度 礼盒装500ml*2瓶白酒 口感绵柔浓香型");


        List l = Lists.newArrayList(goodsInfo1);

//        goodsInfoRepository.saveAll(l);
    }

    @Test
    public void getIndex() {
//        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().;
//        Optional<GoodsInfo> goodsInfo = goodsInfoRepository.search(builder);
//        System.out.println(JSON.toJSON(goodsInfo));
    }

    @Test
    public void deleteIndex() {
//        goodsInfoRepository.deleteById("1551951543908");
//        goodsInfoRepository.deleteAll();

//        elasticsearchTemplate.deleteIndex("goodsindex");
//        elasticsearchTemplate.createIndex();
    }

    @Test
    public void updateIndex() {
        GoodsInfo goodsInfo = new GoodsInfo("", "", "");
//        goodsInfoRepository.save(goodsInfo);
    }

    @Test
    public void esTemplate() {
        Map map =  elasticsearchTemplate.getMapping("goodsindex","goods");
        System.out.println(JSON.toJSON(map));
    }


}
