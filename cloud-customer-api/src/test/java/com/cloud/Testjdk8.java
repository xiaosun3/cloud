package com.cloud;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import lombok.Data;
import org.junit.Test;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sunhaidi on 2019-03-13.
 * 资料参考  https://juejin.im/post/5b6d801af265da0f926bb2a2
 */
public class Testjdk8 {

    @Test
    public void jdk8() {
        List<String> list = Arrays.asList("A", "B", "C", "D");

        list.forEach((String s) -> {
//            System.out.println(s);
        });

        BiConsumer<Integer, String> b = (Integer x, String y) -> System.out.println(x + " : " + y);

        predicateTest(list, s -> "A".equals(s));

        Stream<String> stream = list.stream().filter(s -> "A".equals(s));
        List<String> l = stream.collect(Collectors.toList());
        System.out.println(JSON.toJSON(l));

        Function<String, Integer> f1 = (String t) -> Integer.valueOf(t) * 10;
        System.out.println(f1.apply("3"));

        int value = Stream.of(1, 2, 3, 4).reduce(100, (sum, item) -> sum + item);
        System.out.println(value);

    }

    @Test
    public void functionApply() {
        int r = functionApply(2, integer -> integer - 1);
        System.out.println(r);
    }

    @Test
    public void consumerAccept() {
        consumerAccept(3, (x) -> System.out.println(x * 2));
        consumerAccept(2, integer -> {
            System.out.println(integer * 2);
        });
    }

    /**
     * Predicates
     */
    @Test
    public void predicates() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //输出大于5的数字
        List<Integer> result = conditionFilter(list, integer -> {
            return false;
        });
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
    public static <T> List<T> conditionFilter(List<T> list, Predicate<T> predicate) {
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


    @Test
    public void stream() {
        List<Buss> bussList = new ArrayList<>();
        bussList.add(new Buss("a", 10, 0.3));
        bussList.add(new Buss("b", 3, 0.8));
        bussList.add(new Buss("c", 5, 2.0));
        bussList.add(new Buss("b", 30, 3.2));
        bussList.add(new Buss("c", 20, 0.1));

        bussList.stream()
                .collect(Collectors.groupingBy(Buss::getName)) //分组(Name can't be null)
                .forEach((k, v) -> {
                    Optional<Buss> sum = v.stream().reduce((v1, v2) -> {  //合并
                        v1.setCount(v1.getCount() + v2.getCount());
                        v1.setValue(v1.getValue() + v2.getValue());
                        return v1;
                    });
                    System.out.println(sum);
                    bussList.add(sum.orElse(new Buss("other", 0, 0.0)));

                });
//        st.forEach(buss -> {
//            System.out.println(JSON.toJSON(buss));
//        });

        bussList.stream()
                .collect(Collectors.groupingBy(Buss::getName)).forEach((s, busses) -> {
            System.out.println(JSON.toJSON(s));
            System.out.println(JSON.toJSON(busses));
        });


        List<Map<String, Object>> sublist = Arrays.asList(ImmutableMap.of("1","2"));


        Map<String, Object> merged = sublist.stream()
                .map(Map::entrySet)
                .flatMap(Set::stream)
                .distinct()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Data
    class Buss {

        private String name;
        private int count;
        private double value;

        public Buss(String name, int count, double value) {
            this.name = name;
            this.count = count;
            this.value = value;
        }
    }

    @Test
    public void stream1() {
        List<Test1> test1s = new ArrayList<>();

        Test1 test1_1 = new Test1();
        test1_1.setName("test1_1");

        List<Test2> test2_1 = new ArrayList<>();
        test2_1.add(new Test2("test2.1", 1));
        test2_1.add(new Test2("test2.2", 2));
        test2_1.add(new Test2("test2.3", 3));

        test1_1.setChilds(test2_1);

        Test1 test1_2 = new Test1();
        test1_2.setName("test1_2");

        List<Test2> test2_2 = new ArrayList<>();
        test2_2.add(new Test2("test2.1", 1));
        test2_2.add(new Test2("test2.2", 2));
        test2_2.add(new Test2("test2.3", 3));

        test1_2.setChilds(test2_2);

        test1s.add(test1_1);
        test1s.add(test1_2);


        test1s.stream().forEach(test11 -> {
            List<Test2> test2List = test11.getChilds();
            System.out.println("test1_name:" + test11.getName());
            test2List.forEach(test2 -> {
                System.out.println("test2_name:" + test2.getName());
            });
        });


//        test1s.stream().forEach();



    }


    @Data
    class Test1 {
        String name;
        List<Test2> childs;
    }

    @Data
    class Test2 {
        String name;
        Integer value;

        public Test2(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
    }
}
