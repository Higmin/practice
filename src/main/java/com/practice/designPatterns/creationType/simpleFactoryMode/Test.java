package com.practice.designPatterns.creationType.simpleFactoryMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 8:54
 * @Description : TODO用一句话描述此类的作用
 */
public class Test {
    public static void main(String[] args) {
        Food noodle = FoodFactory.makeFood("Noodle");
        noodle.getName("兰州拉面");

        Food chicken = FoodFactory.makeFood("Chicken");
        chicken.getName("鸡腿");
    }
}
