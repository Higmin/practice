package com.practice.designPatterns.creationType.abstractFactoryMode;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:09
 * @Description : 抽象工厂 : 当涉及到产品族的时候，就需要引入抽象工厂模式了。
 * 当然，抽象工厂的问题也是显而易见的，比如我们要加个显示器，就需要修改所有的工厂，给所有的工厂都加上制造显示器的方法。这有点违反了对修改关闭，对扩展开放这个设计原则。
 */
public interface  ComputerFactory {
    /**
     * 创建CPU对象
     * @return CPU对象
     */
    public Cpu createCpu();
    /**
     * 创建主板对象
     * @return 主板对象
     */
    public Mainboard createMainboard();
}
