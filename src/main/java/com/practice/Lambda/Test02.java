package com.practice.Lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author Higmin
 * @date 2019/7/17 10:27
 *
 * 什么是Lambda表达式:
 *  Java 8的一个大亮点是引入Lambda表达式，使用它设计的代码会更加简洁。
 *  当开发者在编写Lambda表达式时，也会随之被编译成一个函数式接口。
 *
 *  本类中 示例为：Lambda用途 2:集合批量操作
 **/
public class Test02 {
    public static void main(String[] args) {
        // 举例：创建集合，并输出集合元素:下面打印list集合的两种写法是等价的。
        List<String> list = Arrays.asList("a","b","c");
        for (String str : list){
            System.out.println(str);
        }

        System.out.println("======= Lambda 写法 =========");
        // Lambda 写法:
        list.forEach((e) -> System.out.println(e));
    }
}
