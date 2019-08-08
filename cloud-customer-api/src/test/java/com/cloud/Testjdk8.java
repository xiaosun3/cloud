package com.cloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.util.Assert;
import sun.misc.BASE64Decoder;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sunhaidi on 2019-03-13.
 */
public class Testjdk8 {

    @Test
    public void jdk8() {
        List<String> list = Arrays.asList("A", "B", "C", "D");

        list.forEach((String s) -> {
            System.out.println(s);
        });

        BiConsumer<Integer, String> b = (Integer x, String y) -> System.out.println(x + " : " + y);

        predicateTest(list, s -> "A".equals(s));

        Stream<String> stream = list.stream().filter(s -> "A".equals(s));
        List<String> l = stream.collect(Collectors.toList());
        System.out.println(JSON.toJSON(l));

        Function<String, Integer> f1 = (String t) -> Integer.valueOf(t) * 10;
        System.out.println(f1.apply("3"));

    }

    @Test
    public void functionApply(){
        int r = functionApply(2, integer -> integer -1);
        System.out.println(r);
    }

    @Test
    public void consumerAccept(){
        consumerAccept(3, (x) -> System.out.println(x * 2));
        consumerAccept(2, integer -> {
            System.out.println(integer * 2);
        });
    }

    /**
     * Predicates
     */
    @Test
    public void predicates(){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //输出大于5的数字
        List<Integer> result = conditionFilter(list,integer -> {return false;});
        result.forEach(System.out::println);
        System.out.println("-------");
        //输出大于等于5的数字
        result = conditionFilter(list, integer -> integer >= 5);
        result.forEach(System.out::println);
        System.out.println("-------");
        //输出小于8的数字
        result = conditionFilter(list, integer -> integer < 8);
        result.forEach(System.out::println);
        System.out.println("-------");
        //输出所有数字
        result = conditionFilter(list, integer -> true);
        result.forEach(System.out::println);
        System.out.println("-------");
    }

    //高度抽象的方法定义，复用性高
    public static <T> List<T> conditionFilter(List<T> list, Predicate<T> predicate){
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public static void predicateTest(List<String> list, Predicate<String> predicate) {
        for (String n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    static int functionApply(int valueToBeOperated, Function<Integer, Integer> function) {
        return function.apply(valueToBeOperated);
    }

    public static void consumerAccept(int value, Consumer<Integer> consumer) {
        consumer.accept(value);
    }


    public static void main(String[] args) {

        String str = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:getPtJySfqyResponse xmlns:ns2=\"http://com.wondersgroup.jkda.application.modules.webservice\"><ptJySfqy>{\"code\":\"1\",\"data\":{\"jzdz\":\"昌平区北企北企马池口村103号\",\"sfqy\":\"1\",\"team_id\":\"8\",\"team_inst_name\":\"马池口社区卫生服务站\",\"team_name\":\"武春兰家庭医生团队\",\"ysxxs\":[{\"doctor_code\":\"mckmckwcl\",\"doctor_name\":\"武春兰\",\"doctor_post\":\"团队长\",\"doctor_postitle\":\"执业医师\",\"doctor_type\":\"注册全科医生\",\"id_card\":\"110221197105211227\",\"inst_name\":\"马池口社区卫生服务站\"},{\"doctor_code\":\"mcklhj\",\"doctor_name\":\"李慧娟\",\"doctor_post\":\"社区护理\",\"doctor_postitle\":\"护师\",\"doctor_type\":\"社区护士\",\"id_card\":\"110226199003070540\",\"inst_name\":\"马池口社区卫生服务站\"},{\"doctor_code\":\"mckfbwj\",\"doctor_name\":\"王佳\",\"doctor_post\":\"社区公共卫生\",\"doctor_postitle\":\"公卫人员\",\"doctor_type\":\"社区防保人员\",\"id_card\":\"110221198711094627\",\"inst_name\":\"马池口社区卫生服务中心\"},{\"doctor_code\":\"mckzx\",\"doctor_name\":\"赵旭\",\"doctor_post\":\"药事\",\"doctor_postitle\":\"药师\",\"doctor_type\":\"药师\",\"id_card\":\"110106198702061832\",\"inst_name\":\"马池口社区卫生服务站\"},{\"doctor_code\":\"mckwcl\",\"doctor_name\":\"武春兰\",\"doctor_post\":\"其它\",\"doctor_postitle\":\"副主任医师\",\"doctor_type\":\"执业医师\",\"id_card\":\"110221197105211227\",\"inst_name\":\"马池口社区卫生服务中心\"},{\"doctor_code\":\"mckmckzj\",\"doctor_name\":\"周健\",\"doctor_post\":\"专科医生\",\"doctor_postitle\":\"执业医师\",\"doctor_type\":\"注册全科医生\",\"id_card\":\"110106198001030312\",\"inst_name\":\"马池口社区卫生服务站\"}]},\"msg\":\"患者已签约\"}</ptJySfqy></ns2:getPtJySfqyResponse></soap:Body></soap:Envelope>";
        Pattern pattern = Pattern.compile("<ptJySfqy>(.*?)</ptJySfqy>");
        Matcher match = pattern.matcher(str);
        if(match.find()){
            String json = match.group(1);
            JSONObject object = JSON.parseObject(json);
            String code = object.getString("code");
            if("1".equals(code)){
                String sfqy = object.getJSONObject("data").getString("sfqy");
                System.out.println("sfqy:" + sfqy);
            }
        }

    }



}
