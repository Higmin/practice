package com.practice.algorithm.sort;

import java.util.Arrays;

/**
 * @author Higmin
 * @date 2020/3/20 14:25
 *
 * 冒泡排序   时间复杂度：最差为 O( n^2 )  最优是 O(n)
 *           空间复杂度为：O(1)
 * 定义：冒泡排序是一种简单的排序算法。和选择排序相似，可以说冒泡排序是选择排序的升华版。都是寻找最大（最小）元素，然后排序，不用的是寻找的方式。
 * 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
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
		// 长度为 length 的数组，不断把最大的数排在最后边，所以把 length - 1 个最大的数排序完成，即可确定最终的排序。因此 i < s.length - 1
		for (int i = 0; i < s.length - 1; i ++) {
			// todo 重点：由于是相邻的两个数比较，所以需要确定的最大角标是 末尾的前一位; 然后每确定一个数，循环的次数就可以减1，所以 j < s.length - 1 -i
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
