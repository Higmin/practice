package com.practice.designPatterns.structuralType.adaptorMode.objectAdapter;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/27 15:32
 * @Description : 适配器模式： 对象适配器   把野鸡 适配成鸭子
 */
public class Test {

    public static void main(String[] args) {
        Cock wildCock = new WildCock();//有一只野鸡
        Duck duck = new CockAdapter(wildCock);// 成功将野鸡适配成鸭
        duck.quack();
        duck.fly();
    }
}
