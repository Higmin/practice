package com.practice.designPatterns.behaviorType.strategyMode;

/**
 * 折扣接口
 */
public interface MemberStrategy {

    /**
     * 计算图书的价格
     * @param booksPrice    图书的原价
     * @return    计算出打折后的价格
     */
    double calcPrice(double booksPrice);
}
