package org.likexin.algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 带重复元素的子集：
 * Created by likexin5 on 2017/9/9.
 */
public class SubsetsWithDup {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();

        if (nums == null)
            return results;

        if (nums.length == 0) {
            results.add(new ArrayList<>());
            return results;
        }

        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<>(), results);
        return results;
    }

    private static void helper(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> results) {
        results.add(new ArrayList<>(subset));
        for (int i = startIndex; i < nums.length; i++) {
            if (i != startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            helper(nums, i + 1, subset, results);
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsWithDup sub = new SubsetsWithDup();
        int[] nums = {1, 2, 2};
        System.out.println(sub.subsetsWithDup(nums));
    }

}
