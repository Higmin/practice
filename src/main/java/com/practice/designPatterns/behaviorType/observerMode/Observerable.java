package com.exercise.designPatterns.behaviorType.observerMode;

/***
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 * @author guojianmin
 *
 */
public interface Observerable {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObserver();

}
