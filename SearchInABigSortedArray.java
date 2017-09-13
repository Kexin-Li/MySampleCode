package org.likexin.binarysearch;

/**
 * Search In A Big Sorted Array
 * Created by likexin5 on 2017/9/13.
 */
public class SearchInABigSortedArray {

    /**
     * 先用倍增法确定end的长度，这个end不需要是整个数组的长度，只需要是一个包括了target的长度就OK。
     * 接着用二分法查找target的位置，需要注意的是需要求的是第一次出现的位置，所以当reader[mid] == target时，应该移动end，继续往前查找。
     * @param reader 一个不知道边界的超级大数组
     * @param target 目标元素
     * @return 返回目标元素在数组中第一次出现的位置下标
     */
    public int searchBigSortedArray(int[] reader, int target) {
        if (reader == null) {
            return -1;
        }
        int end = 1;
        while (reader[end] < target) {
            end *= 2;
        }
        int start = 0;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (reader[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (reader[start] == target) {
            return start;
        }
        if (reader[end] == target) {
            return end;
        }
        return -1;
    }

}
