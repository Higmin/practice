package com.exercise.designPatterns.creationType.abstractFactoryMode.intel;

import com.exercise.designPatterns.creationType.abstractFactoryMode.Mainboard;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:17
 * @Description : TODO用一句话描述此类的作用
 */
public class IntelMainboard implements Mainboard {
    /**
     * CPU插槽的孔数
     */
    private int cpuHoles = 0;

    public IntelMainboard() {
    }

    public IntelMainboard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        System.out.println("Intel主板的CPU插槽孔数是：" + cpuHoles);
    }
}
