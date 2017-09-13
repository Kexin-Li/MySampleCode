package org.likexin.binarysearch;

/**
 * Find Peak Element
 * Created by likexin5 on 2017/9/13.
 */
public class FindPeakElement {

    /**
     * 判断A[mid]与A[mid+1]，如果A[mid]<A[mid+1]，由于A[A.length - 2] > A[A.length - 1]这一段是下坡，所以mid到mid+1是上坡的话就一定有峰值。
     * 即：A[mid]<A[mid+1]时定位在右半边继续查找，反之在左半边查找。
     * @param A 一个A[0] < A[1] && A[A.length - 2] > A[A.length - 1], A[P] > A[P-1] && A[P] > A[P+1]这样跌宕起伏的数组
     * @return 返回任意一个峰值的下标
     */
    public int findPeak(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        FindPeakElement f = new FindPeakElement();
        int[] A = {1,2,4,5,6,7,8,6};
        System.out.println(f.findPeak(A));
    }

}
