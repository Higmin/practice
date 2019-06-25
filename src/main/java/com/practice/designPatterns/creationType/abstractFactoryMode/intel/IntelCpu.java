package com.practice.designPatterns.creationType.abstractFactoryMode.intel;

import com.practice.designPatterns.creationType.abstractFactoryMode.Cpu;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:16
 * @Description : TODO用一句话描述此类的作用
 */
public class IntelCpu implements Cpu {

    /**
     * 脚针数
     */
    private Integer pins = 0;

    public IntelCpu() {
    }

    public IntelCpu(Integer pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("Intel cpu 的针脚数为："+pins);
    }
}
