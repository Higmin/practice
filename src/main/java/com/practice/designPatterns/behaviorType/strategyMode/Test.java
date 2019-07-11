package com.practice.designPatterns.behaviorType.strategyMode;

/**
 * 策略模式的结构
 * 策略模式是对算法的包装，是把使用算法的责任和算法本身分割开来，委派给不同的对象管理。
 * 策略模式通常把一个系列的算法包装到一系列的策略类里面，作为一个抽象策略类的子类。用一句话来说，就是：“准备一组算法，并将每一个算法封装起来，使得它们可以互换”
 *
 * 使用场景:
 * 　　假设现在要设计一个贩卖各类书籍的电子商务网站的购物车系统。一个最简单的情况就是把所有货品的单价乘上数量，但是实际情况肯定比这要复杂。比如，本网站可能对所有的高级会员提供每本20%的促销折扣；对中级会员提供每本10%的促销折扣；对初级会员没有折扣。
 *
 * 　　根据描述，折扣是根据以下的几个算法中的一个进行的：
 *
 * 　　算法一：对初级会员没有折扣。
 *
 * 　　算法二：对中级会员提供10%的促销折扣。
 *
 * 　　算法三：对高级会员提供20%的促销折扣。
 *
 */
public class Test {

    public static void main(String[] args) {
        //1.选择并创建需要使用的策略对象
        //高级会员
        AdvancedMemberStrategy strategy = new AdvancedMemberStrategy();

        //初级会员
        PrimaryMemberStrategy primaryMemberStrategy = new PrimaryMemberStrategy();
        //创建支付环境
        Price price = new Price(strategy);
        Price price1 = new Price(primaryMemberStrategy);
        //计算价格
        double quote = price.quote(500);
        double quote1 = price1.quote(500);
        System.out.println("高级会员 ==> 图书的最终价格为：" + quote);
        System.out.println("普通会员 ==> 图书的最终价格为：" + quote1);
    }
}
