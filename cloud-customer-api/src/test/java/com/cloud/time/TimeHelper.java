package com.cloud.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * jdk1.8 日期
 * 资料：https://www.jianshu.com/p/f4abe1e38e09
 * Created by sunhaidi on 2019-12-16.
 */
public class TimeHelper {

    @Test
    public void localDate() {
        // 1. 获取当前日期(年月日) -----打印输出-----2018-01-29
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        // 2. 根据年月日构建Date ----打印输出-----2018-01-30
        LocalDate localDate1 = LocalDate.of(2018, 01, 30);
        // 3. 字符串转换日期,默认按照yyyy-MM-dd格式，也可以自定义格式 -----打印输出-----2018-01-30
        LocalDate localDate2 = LocalDate.parse("2018-01-30");
        // 4. 获取本月第一天 -----打印输出-----2018-01-01
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        // 5. 获取本月第二天  -----打印输出-----2018-01-02
        LocalDate secondDayOfMonth = localDate.withDayOfMonth(2);
        // 6. 获取本月最后一天 -----打印输出-----2018-01-31
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        // 7. 明天 -----打印输出----- 2018-01-30
        LocalDate tomorrowDay = localDate.plusDays(1L);
        // 8. 昨天 -----打印输出----- 2018-01-28
        LocalDate yesterday = localDate.minusDays(1L);
        // 9. 获取本年第12天 -----打印输出----- 2018-04-30
        LocalDate day = localDate.withDayOfYear(120);
        // 10. 计算两个日期间的天数
        long days = localDate.until(localDate1, ChronoUnit.DAYS);
        System.out.println(days);
        // 11. 计算两个日期间的周数
        long weeks = localDate.until(localDate1, ChronoUnit.WEEKS);
        System.out.println(weeks);
    }

    @Test
    public void localTime() {
        // 1. 获取当前时间，包含毫秒数 -----打印输出----- 21:03:26.315
        LocalTime localTime = LocalTime.now();
        // 2. 构建时间 -----打印输出----- 12:15:30
        LocalTime localTime1 = LocalTime.of(12, 15, 30);
        // 3. 获取当前时间，不包含毫秒数 -----打印输出----- 21:01:56
        LocalTime localTime2 = localTime.withNano(0);
        // 4. 字符串转为时间，还可以有其他格式，比如12:15, 12:15:23.233
        // -----打印输出----- 12:15:30
        LocalTime localTime3 = LocalTime.parse("12:15:30");
        System.out.println(localTime3.toString());


        LocalDate localDate = LocalDate.now();
        // 1. 本月第一天
        LocalDate firstDayOfMonth = localDate.with(TemporalAdjusters.firstDayOfMonth());
        // 2. 本月最后一天
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        // 3. 本年第一天
        LocalDate firstDayOfYear = localDate.with(TemporalAdjusters.firstDayOfYear());
        // 4. 下个月第一天
        LocalDate firstDayOfNextMonth = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
        // 5. 本年度最后一天
        LocalDate lastDayOfYear = localDate.with(TemporalAdjusters.lastDayOfYear());

        System.out.println(firstDayOfMonth);
        System.out.println(lastDayOfMonth);
        System.out.println(firstDayOfYear);
        System.out.println(firstDayOfNextMonth);
        System.out.println(lastDayOfYear);
    }

    @Test
    public void localDateTime(){
        // 1. 获取当前年月日 时分秒 -----打印输出----- 2018-01-29T21:23:26.774
        LocalDateTime localDateTime = LocalDateTime.now();
        // 2. 通过LocalDate和LocalTime构建 ----- 打印输出----- 2018-01-29T21:24:41.738
        LocalDateTime localDateTime1 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        // 3. 构建年月日 时分秒 -----打印输出----- 2018-01-29T19:23:13
        LocalDateTime localDateTime2 = LocalDateTime.of(2018, 01, 29, 19, 23, 13);
        // 4. 格式化当前时间 ----打印输出----- 2018/01/29
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(formatter.format(localDateTime2));
    }

}
