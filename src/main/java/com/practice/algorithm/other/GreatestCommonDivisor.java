package com.practice.algorithm.other;

/**
 * @author Higmin
 * @date 2020/5/25 14:06
 *
 * 求 最大公约数
 *
 *  辗转相除法（欧几里得算法）
 *
 *  原理： 欧几里得的反转相除法
 *
 * 104 和 40的最大公约数：
 *
 * 104 ÷ 40 = 2 。。。 24
 *
 * 40 ÷ 24 = 1 。。。 16
 *
 * 24 ÷ 16 = 1 。。。 8
 *
 * 16 ÷ 8 = 2
 *
 * 没有余数，则8就是最大公约数
 **/
public class GreatestCommonDivisor {

	public static void main(String[] args) {
		System.out.println("104, 40 的最大公约数为 ：" + getGCD(104, 40));
	}

	public static int getGCD(int num1, int num2){
		// 先获得绝对值，保证负数也可以求
		num1 = Math.abs(num1);
		num2= Math.abs(num2);
		while (true) {
			int remainder = num1 % num2;
			if (remainder == 0) return num2;
			num1 = num2;
			num2 = remainder;
		}
	}
}
