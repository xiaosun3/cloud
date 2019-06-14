package con.cloud;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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

        evaluate(list, s -> "A".equals(s));

        Stream<String> stream = list.stream().filter(s -> "A".equals(s));
        List<String> l = stream.collect(Collectors.toList());
        System.out.println(JSON.toJSON(l));

        Function<String, Integer> f1 = (String t) -> Integer.valueOf(t) * 10;
        System.out.println(f1.apply("3"));

    }

    @Test
    public void testConsumer(){
        String name = "";
        String name1 = "12345";

        validInput(name, inputStr ->
                System.out.println(inputStr.isEmpty() ? "名字不能为空":"名字正常"));
        validInput(name1, inputStr ->
                System.out.println(inputStr.isEmpty() ? "名字不能为空":"名字正常"));

        System.out.println(Long.MAX_VALUE);

    }

    public static void validInput(String name, Consumer<String> function) {
            function.accept(name);
    }

    /**
     * Predicates
     */
    @Test
    public void testPredicates(){
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

    public static void evaluate(List<String> list, Predicate<String> predicate) {
        for (String n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(URLEncoder.encode("郑多儿"));
//        System.out.println(System.currentTimeMillis());
        long a = 4294967296L;
        System.out.println(2^10);
        System.out.println(1 << 29);
        System.out.println(2 << 2);
        System.out.println(2 * 2* 2* 2);

        test1();

    }

    public static int test1(){
        return 1;
    }

}
