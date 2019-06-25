package com.practice.io;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/25 14:57
 * @Description : 自定义计算类（加减乘除）
 *
 * 顺序：BIO（同步阻塞）-->NIO（同步非阻塞）-->AIO（异步非阻塞）-->Netty
 */
public class Calculator {
    public static double addition(double a, double b) {
        return a + b;
    }

    public static double subtraction(double a, double b) {
        return a - b;
    }

    public static double multiplication(double a, double b) {
        return a * b;
    }

    public static double division(double a, double b) {
        return a / b;
    }

    public static double cal(String expression) {
        String substring = expression.substring(1, 2);
        int a = Integer.valueOf(expression.substring(0, 1));
        int b = Integer.valueOf(expression.substring(2, 3));
        if (substring.equals("+")) return addition(a, b);
        if (substring.equals("-")) return subtraction(a, b);
        if (substring.equals("*")) return multiplication(a, b);
        if (substring.equals("/")) return division(a, b);
        return 0;
    }
}
