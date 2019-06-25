package com.exercise.designPatterns.creationType.abstractFactoryMode.amd;

import com.exercise.designPatterns.creationType.abstractFactoryMode.Cpu;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:16
 * @Description : TODO用一句话描述此类的作用
 */
public class AMDCpu implements Cpu {

    /**
     * 脚针数
     */
    private Integer pins = 0;

    public AMDCpu() {
    }

    public AMDCpu(Integer pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("AMD cpu 的针脚数为："+pins);
    }
}
