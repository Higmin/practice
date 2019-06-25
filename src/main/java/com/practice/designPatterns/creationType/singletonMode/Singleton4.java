package com.practice.designPatterns.creationType.singletonMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 17:11
 * @Description : 枚举  单例模式
 * 单元素的枚举类型已经成为实现Singleton的最佳方法。用枚举来实现单例非常简单，只需要编写一个包含单个元素的枚举类型即可。
 * 当第一次访问SingletonEnum.INSTANCE时才调用构造方法去创建该对象,并且enum变量的创建是线程安全的.
 */
public enum  Singleton4 {

    /**
     * 定义一个枚举的元素，它就代表了Singleton4的一个实例。
     */
    INSTANCE;

    /**
     * 单例可以有自己的操作
     */
    public void singletonOperation(){
        //功能处理
    }
}
/**
 *
 * 以下为 简单使用
 *
 * public enum Elvis {
 *
 *     INSTANCE("wangfabo", 1);
 *     private final int id;
 *     private final String name;
 *
 *     Elvis(String n, int i) {
 *         id=i;
 *         name=n;
 *     }
 *
 *     public void leaveTheBuilding() {
 *         System.out.println("name:"+name+" id:"+id);;
 *     }
 *     public Elvis getInstance(){
 *         return INSTANCE;
 *     }
 * }
 */

