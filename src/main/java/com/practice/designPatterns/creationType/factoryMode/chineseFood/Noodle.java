package com.exercise.designPatterns.creationType.factoryMode.chineseFood;

import com.exercise.designPatterns.creationType.factoryMode.Food;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 8:48
 * @Description : TODO用一句话描述此类的作用
 */
public class Noodle implements Food {
    @Override
    public void getName(String foodName) {
        System.out.println("hello，我属于面食!,我的名字叫："+ foodName);
    }
}
