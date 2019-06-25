package com.exercise.designPatterns.creationType.simpleFactoryMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 8:49
 * @Description : TODO用一句话描述此类的作用
 */
public class Chicken implements Food {
    @Override
    public void getName(String foodName) {
        System.out.println("hello，我属于鸡肉!,我的名字叫："+ foodName);
    }
}
