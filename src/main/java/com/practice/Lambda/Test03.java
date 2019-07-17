package com.practice.Lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author Higmin
 * @date 2019/7/17 10:37
 * 什么是Lambda表达式:
 *  Java 8的一个大亮点是引入Lambda表达式，使用它设计的代码会更加简洁。
 *  当开发者在编写Lambda表达式时，也会随之被编译成一个函数式接口。
 *
 *  本类中 示例为：Lambda用途 3: 流操作
 **/
public class Test03 {
    public static void main(String[] args) {

        //下面是流查询list集合中等于 "a"的数量。
        List<String> list = Arrays.asList("a","b","c");
        long count = list.stream().filter((e) -> "a".equals(e)).count();
        // Java 8  :: 的写法
        long count01 = list.stream().filter("a"::equals).count();
        System.out.println(count);
        System.out.println(count01);

    }
}
