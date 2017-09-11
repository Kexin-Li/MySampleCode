package org.likexin.binarysearch;

import java.util.Map;

/**
 * 排序数组中最接近元素: 在一个排好序的数组 A 中找到 i 使得 A[i] 最接近 target
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
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
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
