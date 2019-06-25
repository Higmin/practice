package com.practice.designPatterns.creationType.factoryMode.americanFood;

import com.practice.designPatterns.creationType.factoryMode.Food;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 9:11
 * @Description : 美国食物类：牛排
 */
public class Steak implements Food {
    @Override
    public void getName(String foodName) {
        System.out.println("hello，我属于牛排!,我的名字叫："+ foodName);
    }
}
