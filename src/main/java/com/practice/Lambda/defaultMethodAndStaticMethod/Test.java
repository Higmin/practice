package com.practice.Lambda.defaultMethodAndStaticMethod;

import com.practice.Lambda.defaultMethodAndStaticMethod.defaultMethod.Defaulable;
import com.practice.Lambda.defaultMethodAndStaticMethod.defaultMethod.DefaultableImpl;
import com.practice.Lambda.defaultMethodAndStaticMethod.defaultMethod.OverridableImpl;
import com.practice.Lambda.defaultMethodAndStaticMethod.staticMethod.MethodFactory;

/**
 * @author Higmin
 * @date 2019/7/25 10:33
 *
 * 把接口的静态方法和默认方法放在一起的示例（::new 是构造方法引用,也可以使用 Lambda 表达式）：
 *
 **/
public class Test {
    public static void main(String[] args) {

        System.out.println("测试不实现接口中定义的 默认方法：");
        // Lambda 表达式的写法
        Defaulable defaulable01 = MethodFactory.create(() -> new DefaultableImpl());
        System.out.println("Lambda 表达式的写法 ==> " + defaulable01.notRequired() );

        Defaulable defaulable02 = MethodFactory.create(DefaultableImpl::new);
        System.out.println(":: 的写法 ==> " +defaulable02.notRequired());

        System.out.println();
        System.out.println("测试重写接口中定义的 默认方法：");
        // Lambda 表达式的写法
        Defaulable defaulable03 = MethodFactory.create(() -> new OverridableImpl());
        System.out.println("Lambda 表达式的写法 ==> " + defaulable03.notRequired());
        // :: 的写法
        Defaulable defaulable04 = MethodFactory.create(OverridableImpl::new);
        System.out.println(":: 的写法 ==> " + defaulable04.notRequired());

    }
}
