package com.practice.designPatterns.creationType.factoryMode.factory;

import com.practice.designPatterns.creationType.factoryMode.Food;
import com.practice.designPatterns.creationType.factoryMode.FoodFactory;
import com.practice.designPatterns.creationType.factoryMode.chineseFood.Chicken;
import com.practice.designPatterns.creationType.factoryMode.chineseFood.Noodle;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 9:03
 * @Description : 工厂模式----1. 中国食物工厂类
 */
public class ChineseFoodFactory implements FoodFactory {
    @Override
    public Food makeFood(String name) {
        if (name.equals("A")) {
            return new Chicken();
        } else if (name.equals("B")) {
            return new Noodle();
        } else {
            return null;
        }
    }
}
