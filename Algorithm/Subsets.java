package org.likexin.algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集：
 *
 * Created by likexin5 on 2017/9/8.
 */
public class Subsets {

    /**
     * 还有 Bug：不是出现了所有子集
     * @param nums 待处理集合
     * @return 所有子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            list.clear();
            dfs(nums, 0, i, list, result);
        }
        return result;
    }

    private static void dfs(int[] nums, int start, int size, List<Integer> list, List<List<Integer>> result) {
        if (list.size() == size) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i + 1, size, list, result);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets sub = new Subsets();
        int[] nums = {1, 2, 3};
        System.out.println(sub.subsets(nums));
    }

}
