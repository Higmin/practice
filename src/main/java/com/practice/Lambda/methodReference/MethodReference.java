package com.practice.Lambda.methodReference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Higmin
 * @date 2019/7/17 11:34
 *
 *  方法引用 :
 *  方法引用提供了一个很有用的语义来直接访问类或者实例的已经存在的方法或者构造方法。
 *  结合Lambda表达式，方法引用使语法结构紧凑简明。不需要复杂的引用。
 *
 **/
public class MethodReference {

    // 第一种方法引用是构造方法引用，语法是：Class::new ，对于泛型来说语法是：Class<T >::new，请注意构造方法没有参数:
    public static MethodReference create(final Supplier<MethodReference> supplier){
        return supplier.get();
    }

    // 第二种方法引用是静态方法引用，语法是：Class::static_method请注意这个静态方法只支持一个类型为 MethodReference 的参数。
    public static void collide(final MethodReference methodReference){
        System.out.println("collide" + methodReference.toString());
    }

    // 第三种方法引用是类实例的方法引用，语法是：Class::method请注意方法没有参数。
    public void repair(){
        System.out.println("Repaired " + this.toString());
    }

    // 最后一种方法引用是引用特殊类的方法，语法是：instance::method，请注意只接受 MethodReference 类型的一个参数。
    public void follow(final MethodReference another){
        System.out.println("Following the " + another.toString());
    }

    public static void main(String[] args) {

        // 构造方法引用
        final MethodReference test = MethodReference.create(MethodReference::new);
        final List<MethodReference> tests = Arrays.asList(test);
        // 静态方法引用
        tests.forEach(MethodReference::collide);
        // 类实例的方法引用
        tests.forEach(MethodReference::repair);
        // 引用特殊类的方法
        tests.forEach(test::follow);

    }
}
