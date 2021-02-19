package com.practice.algorithm.leetcode_passing;

/**
 * 合并排序的数组
 * <p>
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * <p>
 * 示例:
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 说明:
 * A.length == n + m
 *
 * @author Jimmy
 * @version 1.0, 2021/02/19
 * @since practice 1.0.0
 */
public class N_1001_SortedMergeLCCI {

    // 思路：由于数组 A 和数组 B 都是有序的数组。所以我们考虑双指针从两个数组的头部比较元素大小。
    // 把每次比较中，小的元素放入到一个新的数组中，然后再遍历临时数组的元素，依次放入到数组A中即可。

    public void merge(int[] A, int m, int[] B, int n) {
        // 首先定义双指针。
        int indexA = 0, indexB = 0;
        // 初始化临时数组。用于临时存放数据。
        int[] temp = new int[m + n];
        int indexTemp = 0;
        int cur;
        while (indexA < m || indexB < n) {
            if (indexA == m) { // 判断指针是否超出数组 A 中的元素个数。
                cur = B[indexB++];
            } else if (indexB == n) { // 判断指针是否超出数组 B 中的元素个数。
                cur = A[indexA++];
            } else if (A[indexA] >= B[indexB]) {
                cur = B[indexB++];
            } else {
                cur = A[indexA++];
            }
            temp[indexTemp++] = cur;
            if (indexTemp == m + n) {
                break;
            }
        }

        for (int i = 0; i < temp.length; i++) {
            A[i] = temp[i];
        }
    }
}
