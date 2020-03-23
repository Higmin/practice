package com.practice.algorithm.sort;

import java.util.Arrays;

/**
 * @author Higmin
 * @date 2020/3/23 10:20
 *
 * 选择排序  时间复杂度：最差为 O( n^2 )  最优是 O( n^2 )
 *          空间复杂度为：O(1)
 *
 * 定义：选择排序是一种简单直观的排序算法。和冒泡排序相似，可以说冒泡排序是选择排序的升华版，都是寻找最大（最小）元素，然后排序，不用的是寻找的方式。
 *
 * 基本思想是:
 * 1、首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 * 2、然后再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾
 * 3、以此类推，直到所有元素均排序完毕。n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。
 *
 * 精髓：通过相邻的元素两两比较，寻找最大（最小）的数。不断循环，直到排序完成。本例中以寻找最小的数为例。
 *
 **/
public class SelectionSort {
	public static void main(String[] args) {
		int [] arr = new int[]{67,4,55,26,9,47,15,33,24,18,84,79,66,98};
		System.out.println("排序前： " + Arrays.toString(arr));
		int[] ints = selectionSort(arr);
		System.out.println("排序后： " + Arrays.toString(ints));
	}

	public static int[] selectionSort(int s[]) {
		int temp , minIndex;
		// 长度为 length 的数组，不断把最小的数排在前边，所以把 length - 1 个最小的数排序完成，即可确定最终的排序。因此 i < s.length - 1
		for (int i = 0; i < s.length - 1; i ++) {
			minIndex = i;
			// todo 重点：寻找最小（最大）的数。从前向后排列，直到排序完成。所以每次寻找都是排好序的下一个元素开始，到最后一个元素为止。
			for (int j = i + 1; j < s.length; j ++) {
				if (s[j] < s[minIndex]) {
					minIndex = j;
				}
			}
			// 每次找到最小数的角标之后，开始置换元素。
			temp = s[i];
			s[i] = s[minIndex];
			s[minIndex] = temp;
		}
		return s;
	}
}
