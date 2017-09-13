package org.likexin.binarysearch;

/**
 * Search a 2D Matrix:
 *
 * Created by likexin5 on 2017/9/12.
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int start = 0;
        int end = matrix.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        int row = matrix[end][0] <= target ? end : start;
        start = 0;
        end = matrix[0].length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (matrix[row][end] == target) {
            return true;
        }
        if (matrix[row][start] == target) {
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
        SearchMatrix sMatrix = new SearchMatrix();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        System.out.println(matrix.length);
        System.out.println(sMatrix.searchMatrix(matrix, 14));
    }

}
