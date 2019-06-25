package com.practice.designPatterns.creationType.factoryMode.americanFood;

import com.practice.designPatterns.creationType.factoryMode.Food;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 9:10
 * @Description : 美国食物类：汉堡包
 */
public class Hamburger implements Food {
    @Override
    public void getName(String foodName) {
        System.out.println("hello，我属于汉堡包!,我的名字叫："+ foodName);
    }
}
