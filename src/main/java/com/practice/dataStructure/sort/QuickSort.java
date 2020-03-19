package com.practice.dataStructure.sort;

import java.util.Arrays;

/**
 * @author Higmin
 * @date 2020/3/18 16:12
 *
 * 快速排序  时间复杂度：最差为 O( n^2 )  最优是 O( nlogN )    对数函数以2为底
 *          空间复杂度为：O( nlogN )  对数函数以2为底
 *
 * 基本思想是:
 * 1、先从数列中取出一个数作为基准数
 * 2、分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边
 * 3、再对左右区间重复第二步，直到各区间只有一个数
 *
 * 概括来说为 挖坑填数+分治法
 *
 * 挖坑填数 : 将某个位置的数 a 用临时变量 x 存储起来，那个该位置相当于挖了一个坑，可以通过置换放其他的合适的数，置换完之后坑的位置就变了，
 * 直到找到 a 正确的位置，再将 x 填进去。
 *
 * 分治法的精髓：
 * 分--将问题分解为规模更小的子问题；
 * 治--将这些规模更小的子问题逐个击破；
 * 合--将已解决的子问题合并，最终得出“母”问题的解
 *
 * 参考此博客理解：https://www.cnblogs.com/yueansixing/articles/9125634.html
 **/
public class QuickSort {
	public static void main(String[] args) {
	    int [] arr = new int[]{67,4,55,26,9,47,15,33,24,18,84,79,66,98};
		int[] ints = quickSort(arr, 0, 13);
		System.out.println(Arrays.toString(ints));
	}

	public static int[] quickSort(int s[], int l, int r) // l : left    r : right
	{
		if (l < r)
		{
			// 1. 先挖坑填数的代码:
			// Swap(s[l], s[(l + r) / 2]); // todo 了解其他方式：将中间的这个数和第一个数交换 这里不做实现。解释：有的书上是以中间的数作为基准数的，要实现这个方便非常方便，直接将中间的和第一个数进行交换就可以了。
			int i = l, j = r, x = s[l]; // x : 挖坑的位置所在的数 这里用x临时存储，然后相当于在该位置挖了一个坑，等到确定了该数字的位置再填进去
			while (i < j)
			{
				while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
					j--;
				if(i < j)
					s[i++] = s[j]; // 这里分两步：1. 将s[j]填到s[i]中，s[j]就形成了一个新的坑        2. i++ 指针向后移动一位

				while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
					i++;
				if(i < j)
					s[j--] = s[i]; // 这里分两步：1. 将s[i]填到s[j]中，s[i]就形成了一个新的坑        2. j-- 指针向前移动一位
			}
			s[i] = x;

			// 2. 再写 分治法 的代码:
			quickSort(s, l, i - 1); // 递归调用
			quickSort(s, i + 1, r);
		}
		return s;
	}
}
