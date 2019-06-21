package com.exercise.designPatterns.creationType.abstractFactoryMode;

import com.exercise.designPatterns.creationType.abstractFactoryMode.factory.AmdFactory;
import com.exercise.designPatterns.creationType.abstractFactoryMode.factory.IntelFactory;

/**
 * @Auther : guojianmin
 * @Date : 2019/6/21 16:47
 * @Description : TODO用一句话描述此类的作用
 */
public class Test {
    public static void main(String[] args) {
        ComputerEngineer computerEngineer = new ComputerEngineer();
        ComputerFactory factory = new IntelFactory();
        computerEngineer.makeComputer(factory);

        ComputerFactory factory1 = new AmdFactory();
        computerEngineer.makeComputer(factory1);
    }
}
