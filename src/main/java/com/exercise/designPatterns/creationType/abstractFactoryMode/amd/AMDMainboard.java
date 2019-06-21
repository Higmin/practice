package com.exercise.designPatterns.creationType.abstractFactoryMode.amd;

import com.exercise.designPatterns.creationType.abstractFactoryMode.Mainboard;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:18
 * @Description : TODO用一句话描述此类的作用
 */
public class AMDMainboard implements Mainboard {
    /**
     * CPU插槽的孔数
     */
    private int cpuHoles = 0;

    public AMDMainboard() {
    }

    public AMDMainboard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        System.out.println("AMD主板的CPU插槽孔数是：" + cpuHoles);
    }
}
