package org.likexin.binarysearch;

/**
 * 目标最后位置:给一个升序数组，找到target最后一次出现的位置，如果没出现过返回-1
 *
 * Created by likexin5 on 2017/9/11.
 */
public class LastPosition {

    public int lastPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            return end;
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }

    public static void main(String[] args) {
        LastPosition lPosition = new LastPosition();
        int[] A = {1, 2, 2, 4, 5, 5};
        System.out.println(lPosition.lastPosition(A, 5));
    }

}
