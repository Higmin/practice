package com.practice.designPatterns.structuralType.adaptorMode.objectAdapter;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/27 15:28
 * @Description : 野鸡类 实现 公鸡的接口
 */
public class WildCock implements Cock {
    @Override
    public void gobble() {
        System.out.println("咕咕叫");
    }

    @Override
    public void fly() {
        System.out.println("野鸡也会飞哦");
    }
}
