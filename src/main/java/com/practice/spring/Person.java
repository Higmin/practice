package com.practice.spring;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/20 15:07
 * @Description :
 */
public class Person implements IPerson {
    @Override
    public void doSomething(String work) {
        System.out.println("I want to do something! practice：" + work);
    }
}
