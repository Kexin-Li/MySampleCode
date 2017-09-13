package org.likexin.binarysearch;

/**
 * Search In Rotated Array
 * Created by likexin5 on 2017/9/13.
 */
public class SearchInRotatedArray {

    /**
     * 通过判断A[mid]和A[end]的大小，找出有序的那一半边；再判断target是否在有序的那一半边，在的话就移动start或者end来定位target的下标。
     * @param A 一个原先排好序但以某个元素为轴旋转了一下的数组，这样的数组的特点是可以通过判断A[mid]与A[end]的大小来判断那一半边是有序的
     * @param target 目标元素
     * @return 目标元素在数组中的下标
     */
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[end] < A[mid]) { // 左半边有序
                if (A[start] <= target && A[mid] >= target) { // 符合条件则在左半边
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (A[end] >= target && A[mid] <= target) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }

}
