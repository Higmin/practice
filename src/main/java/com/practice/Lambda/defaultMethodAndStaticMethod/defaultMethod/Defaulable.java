package com.practice.Lambda.defaultMethodAndStaticMethod.defaultMethod;

/**
 * @author Higmin
 * @date 2019/7/25 10:22
 *
 * Java 8增加了两个新的概念在接口声明的时候：
 * 默认和静态方法。
 * 默认方法和 Trait有些类似，但是目标不一样。
 * 默认方法允许我们在接口里添加新的方法，而不会破坏实现这个接口的已有类的兼容性，也就是说不会强迫实现接口的类实现默认方法。
 *
 *
 * 默认方法和抽象方法的区别是：
 * 抽象方法必须要被实现，默认方法不是。
 * 作为替代方式，接口可以提供一个默认的方法实现，所有这个接口的实现类都会通过继承得倒这个方法（如果有需要也可以重写这个方法）
 *
 **/
public interface Defaulable {
    // 接口中定义默认方法
    default String notRequired() {
        return "Default implementation";
    }
}
