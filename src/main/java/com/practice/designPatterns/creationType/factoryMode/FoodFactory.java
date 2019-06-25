package com.practice.designPatterns.creationType.factoryMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 9:03
 * @Description : 工厂模式
 * 之所以需要引入工厂模式，是因为我们往往需要使用两个或两个以上的工厂。
 */
public interface FoodFactory {

    Food makeFood(String name);

}
