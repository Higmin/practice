package com.practice.java8.date;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author Higmin
 * @date 2019/7/26 12:35
 *
 * Java 8引入了新的日期时间API（JSR 310）改进了日期时间的管理。日期和时间管理一直是Java开发人员最痛苦的问题。java.util.Date和后来的java.util.Calendar一点也没有改变这个情况（甚至让人们更加迷茫）。
 *
 * 因为上面这些原因，产生了Joda-Time ，可以替换Java的日期时间API。Joda-Time深刻影响了 Java 8新的日期时间API，Java 8吸收了Joda-Time 的精华。
 * 新的java.time包包含了所有关于日期、时间、日期时间、时区、Instant（跟日期类似但精确到纳秒）、duration（持续时间）和时钟操作的类。
 * 设计这些API的时候很认真地考虑了这些类的不变性（从java.util.Calendar吸取的痛苦教训）。如果需要修改时间对象，会返回一个新的实例。
 *
 **/
public class DateTest {
    public static void main(String[] args) {
        // 首先介绍一下两个概念：绝对时间和时区

        // 1.绝对时间： 是指从1970年01月01日00时00分00秒 到此刻的时间，全世界都一样。
        // 1970年01月01日00时00分00秒(北京时间1970年01月01日08时00分00秒)

        // 2.时区 是符合人们习惯的一种辅助计时方法，按照经线从东到西将绝对时间做了重新划分以方便全球不同经度的地区计时，现今全球共分为24个时区，并且规定相邻区域的时间相差1小时，

        // 示例一：Instant 就是绝对时间    时间戳 就是绝对时间的总秒(或毫秒)数
        Instant instant = Instant.now();
        System.out.println("Instant-绝对时间: " + instant);
        System.out.println("Instant-转换为时间戳: " + instant.toEpochMilli());

        // 示例二：LocalDateTime 是指当前时区的时间，所以LocalDateTime要转时间戳，需要先转为绝对时间，再由绝对是件转换为时间戳

        System.out.println();
        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDateTime - 当前时区的时间 " + now);
        System.out.println("LocalDateTime - 指定时区: " + now.atZone(ZoneId.systemDefault()));
        System.out.println("LocalDateTime - 转换为绝对时间: " + now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("LocalDateTime - 时间戳: " + now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("LocalDateTime - 利用DateTimeFormatter转换为指定格式 " + nowTime);

        // 补充：将String 类型的时间转换为 指定格式
        System.out.println();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse("2019-06-17 10:45:00", formatter);
        System.out.println("将String 类型的时间利用DateTimeFormatter转换为 指定格式: " + formatter.format(date));

        // 示例三：Clock类：Clock使用时区来访问当前的instant, date和time。Clock类可以替换 System.currentTimeMillis() 和 TimeZone.getDefault().
        System.out.println();
        Clock clock = Clock.systemUTC();
        System.out.println("Clock：" + clock.instant());
        System.out.println("Clock - 时间戳：" + clock.millis());
    }
}
