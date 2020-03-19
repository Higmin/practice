package com.practice.dataStructure.sort;

import java.util.Arrays;

/**
 * @author Higmin
 * @date 2020/3/18 16:12
 *
 * 快速排序
 *
 * 基本思想是:
 * 1、先从数列中取出一个数作为基准数
 * 2、分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边
 * 3、再对左右区间重复第二步，直到各区间只有一个数
 *
 * 概括来说为 挖坑填数+分治法
 * 参考此博客理解：https://www.cnblogs.com/yueansixing/articles/9125634.html
 **/
public class QuickSort {
	public static void main(String[] args) {
	    int [] arr = new int[]{4,55,26,9,47,15,33,24,18,84,79,66,98};
		int[] ints = quickSort(arr, 0, 12);
		System.out.println(Arrays.toString(ints));
	}

	public static int[] quickSort(int s[], int l, int r) // l : left    r : right
	{
		if (l < r)
		{
			// 1. 先挖坑填数的代码:
			// Swap(s[l], s[(l + r) / 2]); // 将中间的这个数和第一个数交换 参见注1
			int i = l, j = r, x = s[l];
			while (i < j)
			{
				while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
					j--;
				if(i < j)
					s[i++] = s[j];

				while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
					i++;
				if(i < j)
					s[j--] = s[i];
			}
			s[i] = x;

			// 2. 再写 分治法 的代码:
			quickSort(s, l, i - 1); // 递归调用
			quickSort(s, i + 1, r);
		}
		return s;
	}
}
