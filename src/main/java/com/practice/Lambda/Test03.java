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
 *
 * ->是Java 8新增的Lambda表达式中，变量和临时代码块的分隔符，即：
 *
 * (变量) -> {代码块}
 * 如果代码块只有一个表达式，大括号可以省略。如果变量类型可以自动推断出来，可以不写变量类型。
 * ::是类似于C++的域运算符，获取方法使用的。
 * stream()也是JDK8新增的流，你的表达式中将list转换为流，就可以惰性处理，这样只有变量要用的时候才会被调用，
 *
 *  本类中 示例为：Lambda用途 3: 流操作
 *
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
