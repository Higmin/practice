package com.practice.java8.Lambda.defaultMethodAndStaticMethod.defaultMethod;

/**
 * @author Higmin
 * @date 2019/7/25 10:29
 *
 * 此类用于实现定义默认方法的接口，并重写其中的默认方法
 *
 **/
public class OverridableImpl implements Defaulable {
    @Override
    public String notRequired() {
        return "Overridden implementation";
    }
}
