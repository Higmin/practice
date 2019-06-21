package com.exercise.designPatterns.creationType.factoryMode.factory;

import com.exercise.designPatterns.creationType.factoryMode.Food;
import com.exercise.designPatterns.creationType.factoryMode.FoodFactory;
import com.exercise.designPatterns.creationType.factoryMode.americanFood.Hamburger;
import com.exercise.designPatterns.creationType.factoryMode.americanFood.Steak;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 9:03
 * @Description : 工厂模式----2. 美国食物工厂类
 */
public class AmericanFoodFactory implements FoodFactory {
    @Override
    public Food makeFood(String name) {
        if (name.equals("A")) {
            return new Hamburger();
        } else if (name.equals("B")) {
            return new Steak();
        } else {
            return null;
        }
    }
}
