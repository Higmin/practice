package com.practice.designPatterns.creationType.simpleFactoryMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 8:51
 * @Description : 简单工厂模式
 * 简单地说，简单工厂模式通常就是这样，一个工厂类 XxxFactory，里面有一个静态方法，根据我们不同的参数，返回不同的派生自同一个父类（或实现同一接口）的实例对象。
 */
public class FoodFactory {
    public static Food makeFood(String name){

        if (name.equals("Noodle")){
            Food food = new Noodle();
            return food;
        }else if (name.equals("Chicken")){
            Food food = new Chicken();
            return food;
        }else {
            return null;
        }
    }
}
