package com.practice.algorithm.other;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Higmin
 * @date 2020/5/19 9:36
 *
 * 要求不用数学库，求 sqrt (2)精确到小数点后10位(Java版)
 *
 **/
public class Sqrt {

	public static void main(String[] args) {
		System.out.println("Math.sqrt()计算：" + Math.sqrt(2.0d));
		System.out.println("自己使用二分法实现：" + getResult(2.0d, 1.0, 2.0d));
	}

	private static double getResult(double target, double left, double right){
		double mid = 0;
		// 为了解决位数太多的问题，使用科学计数法：1e-11 表示10的负11次方   1e+11 表示10的11次方
		while(right - left > 1e-11){ // 保留10位小数，所以计算要计算到第11位
			mid = left + (right - left) / 2.0d;
			if (mid * mid < target) left = mid;
			else right = mid;
		}
		// RoundingMode.HALF_UP 四舍五入
		return new BigDecimal(mid).setScale(10, RoundingMode.HALF_UP).doubleValue();
	}

}
