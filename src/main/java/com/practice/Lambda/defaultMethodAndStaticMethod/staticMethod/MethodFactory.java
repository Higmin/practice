package com.practice.Lambda.defaultMethodAndStaticMethod.staticMethod;

import com.practice.Lambda.defaultMethodAndStaticMethod.defaultMethod.Defaulable;

import java.util.function.Supplier;

/**
 * @author Higmin
 * @date 2019/7/25 10:24
 *
 * Java 8增加了两个新的概念在接口声明的时候：
 * 默认和静态方法。
 *
 * 接口里可以声明静态方法，并且可以实现
 *
 **/
public interface MethodFactory {
    // 接口中定义静态方法
    // Supplier 简单地说就是 参数函数化 ， 函数话的参数可以使用 Lambda表达式，见示例Test.java
    static Defaulable create(Supplier<Defaulable> supplier) {
        return supplier.get();
    }

}
