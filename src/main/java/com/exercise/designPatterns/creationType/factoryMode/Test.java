package com.exercise.designPatterns.creationType.factoryMode;

import com.exercise.designPatterns.creationType.factoryMode.factory.AmericanFoodFactory;
import com.exercise.designPatterns.creationType.factoryMode.factory.ChineseFoodFactory;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 9:13
 * @Description : TODO用一句话描述此类的作用
 */
public class Test {
    public static void main(String[] args) {
        FoodFactory foodFactory = new ChineseFoodFactory();
        Food food = foodFactory.makeFood("A");
        food.getName("鸡腿");


        FoodFactory foodFactory1 = new AmericanFoodFactory();
        Food food1 = foodFactory1.makeFood("B");
        food1.getName("西冷牛排");

    }
}
