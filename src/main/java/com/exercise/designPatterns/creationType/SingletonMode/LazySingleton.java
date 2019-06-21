package com.exercise.designPatterns.creationType.SingletonMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 17:04
 * @Description : 　懒汉式单例类
 */
public class LazySingleton {


    //1.私有构造
    private LazySingleton(){}

    //2.创建静态自身对象但是不创建实例。
    private static LazySingleton instance = null;

    //3.初始化自身对象 并对外提供访问接口
    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
