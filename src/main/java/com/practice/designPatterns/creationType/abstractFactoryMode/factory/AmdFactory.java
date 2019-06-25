package com.practice.designPatterns.creationType.abstractFactoryMode.factory;

import com.practice.designPatterns.creationType.abstractFactoryMode.ComputerFactory;
import com.practice.designPatterns.creationType.abstractFactoryMode.Cpu;
import com.practice.designPatterns.creationType.abstractFactoryMode.Mainboard;
import com.practice.designPatterns.creationType.abstractFactoryMode.amd.AMDCpu;
import com.practice.designPatterns.creationType.abstractFactoryMode.amd.AMDMainboard;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:44
 * @Description : TODO用一句话描述此类的作用
 */
public class AmdFactory implements ComputerFactory {
    @Override
    public Cpu createCpu() {
        return new AMDCpu(938);
    }

    @Override
    public Mainboard createMainboard() {
        return new AMDMainboard(938);
    }
}
