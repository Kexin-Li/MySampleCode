package org.likexin.binarysearch;

/**
 * 目标第一次出现的位置:给一个升序数组，找到target第一次出现的位置，如果没出现过返回-1
 *
 * Created by likexin5 on 2017/9/11.
 */
public class FirstPosition {

    public int firstPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[start] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstPosition fPosition = new FirstPosition();
        int[] nums = {2, 3, 4, 6, 8, 8, 8, 10};
        System.out.println(fPosition.firstPosition(nums, 6));
    }

}
