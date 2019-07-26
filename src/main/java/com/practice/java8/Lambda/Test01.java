package com.practice.java8.Lambda;

/**
 * @author Higmin
 * @date 2019/7/17 10:13
 *
 * 什么是Lambda表达式:
 * Java 8的一个大亮点是引入Lambda表达式，使用它设计的代码会更加简洁。
 * 当开发者在编写Lambda表达式时，也会随之被编译成一个函数式接口。
 *
 *
 * ->是Java 8新增的Lambda表达式中，变量和临时代码块的分隔符，即：
 *
 * (变量) -> {代码块}
 * 如果代码块只有一个表达式，大括号可以省略。如果变量类型可以自动推断出来，可以不写变量类型。
 * ::是类似于C++的域运算符，获取方法使用的。
 * stream()也是JDK8新增的流，你的表达式中将list转换为流，就可以惰性处理，这样只有变量要用的时候才会被调用，
 *
 *  本类中 示例为：Lambda用途 1：只有一个抽象方法的函数式接口
 **/
public class Test01 {
    public static void main(String[] args) {
        // 举例：创建线程 , 并开启
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread-01");
            }
        }).start();

        System.out.println("======= Lambda 写法 =========");

        // 只有一个抽象方法的函数式接口
        // 1.Lambda 写法 1
        Runnable run01 = () -> System.out.println("Thread-02");
        new Thread(run01).start();

        // 2.Lambda 写法 2
        new Thread(() -> System.out.println("Thread-03")).start();

        // 3.Lambda 写法 3
        new Thread(() -> print("Thread-04")).start();

        // 4.Lambda 写法 4
        new Thread(() -> {
            String str = "Thread-05";
            System.out.println(str);
        }).start();
    }

    /**
     * 自定义输出 方法
     * @param str
     */
    private static void print(String str){
        System.out.println(str);
    }
}
