package com.practice.dataStructure.sort;

import java.util.Arrays;

/**
 * @author Higmin
 * @date 2020/3/20 14:25
 *
 * 冒泡排序   时间复杂度：最差为 O( n^2 )  最优是 O(n)
 *           空间复杂度为：O(1)
 *
 * 基本思想是:
 * 1、比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 3、针对所有的元素重复以上的步骤，除了最后一个；
 * 4、重复步骤1~3，直到排序完成。
 *
 * 精髓：通过相邻的元素两两比较，寻找最大（最小）的数。不断循环，直到排序完成。本例中以寻找最大的数为例。
 *
 **/
public class BubbleSort {
	public static void main(String[] args) {
		int [] arr = new int[]{67,4,55,26,9,47,15,33,24,18,84,79,66,98};
		System.out.println("排序前： " + Arrays.toString(arr));
		int[] ints = bubbleSort(arr);
		System.out.println("排序后： " + Arrays.toString(ints));
	}

	public static int[] bubbleSort(int s[]) {
		for (int i = 0; i < s.length - 1; i ++) {
			for (int j = 0; j < s.length - 1 -i; j++) {
				if (s[j] > s[j + 1]) { // 比较相邻元素大小 ， 如果 s[j] > s[j + 1]  则交换位置。
					int temp = s[j + 1];
					s[j + 1] = s[j];
					s[j] = temp;
				}
			}
		}
		return s;
	}
}
