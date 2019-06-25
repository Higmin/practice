package com.practice.designPatterns.creationType.abstractFactoryMode.factory;

import com.practice.designPatterns.creationType.abstractFactoryMode.ComputerFactory;
import com.practice.designPatterns.creationType.abstractFactoryMode.Cpu;
import com.practice.designPatterns.creationType.abstractFactoryMode.Mainboard;
import com.practice.designPatterns.creationType.abstractFactoryMode.intel.IntelCpu;
import com.practice.designPatterns.creationType.abstractFactoryMode.intel.IntelMainboard;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:43
 * @Description : TODO用一句话描述此类的作用
 */
public class IntelFactory implements ComputerFactory {
    @Override
    public Cpu createCpu() {
        return new IntelCpu(755);
    }

    @Override
    public Mainboard createMainboard() {
        return new IntelMainboard(755);
    }
}
