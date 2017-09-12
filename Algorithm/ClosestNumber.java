package org.likexin.binarysearch;

/**
 * 排序数组中最接近元素: 在一个排好序的数组 A 中找到 i 使得 A[i] 最接近 target
 *
 * 解题思路：
 * 1. 使用二分法找到target在数组中的位置，假如target真的存在数组中则直接返回下标。
 * 2. 如果不存在，使用夹逼后start和end中间则应该是target的位置。这时再来比较start和end谁离target近。
 *
 * Created by likexin5 on 2017/9/11.
 */
public class ClosestNumber {

    /**
     * 二分法
     * @param A 排好序的数组
     * @param target 目标元素
     * @return 与目标最接近的元素的索引
     */
    public int closestNumber(int[] A, int target) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0;
        int end = A.length - 1;
        // 边界情况
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return Math.abs(A[start] - target) > Math.abs(A[end] - target) ? end : start;
    }

    public static void main(String[] args) {
        ClosestNumber cNumber = new ClosestNumber();
        int[] A = {1, 3, 4, 8, 9, 10};
        System.out.println(cNumber.closestNumber(A, 5));
    }

}
