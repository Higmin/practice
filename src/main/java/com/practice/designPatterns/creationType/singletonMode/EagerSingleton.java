package com.practice.designPatterns.creationType.singletonMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 17:03
 * @Description : 饿汉式单例类
 */
public class EagerSingleton {

    //1.私有构造
    private EagerSingleton(){}

    //2.创建静态自身对象并赋值。
    private static EagerSingleton instance = new EagerSingleton();

    //3.对外提供访问接口
    public static EagerSingleton getInstance(){
        return instance;
    }
}
