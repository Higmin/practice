package com.practice.designPatterns.creationType.singletonMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 17:05
 * @Description : .静态内部类 单例模式
 */
public class Singleton3 {

    //1.私有构造
    private Singleton3() {}
    //2.静态内部类创建对象。主要是使用了 嵌套类可以访问外部类的静态属性和静态方法 的特性
    private static class Holder {
        private static Singleton3 instance = new Singleton3();
    }
    //3.对外提供访问接口
    public static Singleton3 getInstance() {
        return Holder.instance;
    }
}
