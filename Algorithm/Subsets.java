package org.likexin.algs;

import java.util.*;

/**
 * 子集：给定一个不带重复元素的数组，找出这个数组的所有子集。要求子集中的元素不能是降序排列的。
 * http://www.lintcode.com/zh-cn/problem/subsets/
 * Example:
 * 如果 nums = {1, 2, 3}
 * 那么 result = [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
 *
 * Created by likexin5 on 2017/9/8.
 */
public class Subsets {

    /**
     * DFS
     * @param nums 待处理集合
     * @return 所有子集
     */
    public List<List<Integer>> subsets(int[] nums) {
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

    private static void helper(int[] nums,
                               int startIndex,
                               List<Integer> subset,
                               List<List<Integer>> results) {
        results.add(new ArrayList<>(subset));
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            helper(nums, i + 1, subset, results);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * 用HashMap记录中间结果
     * @param nums 待处理集合
     * @return 所有子集
     */
    public List<List<Integer>> subsets_1(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums == null) {
            return results;
        }

        Arrays.sort(nums);
        return helper_1(nums, 0, new HashMap<Integer, List<List<Integer>>>());
    }

    private static List<List<Integer>> helper_1(int[] nums, int index, Map<Integer, List<List<Integer>>> map) {
        int len = nums.length;

        if (map.containsKey(index)) {
            return map.get(index);
        }

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> pathTmp = new ArrayList<>();
        results.add(pathTmp);

        for (int i = index; i < len; i++) {
            List<List<Integer>> left = helper_1(nums, i + 1, map);
            for (List<Integer> list : left) {
                pathTmp = new ArrayList<>();
                pathTmp.add(nums[i]);
                pathTmp.addAll(list);
                results.add(pathTmp);
            }
        }
        map.put(index, results);
        return results;
    }

    public static void main(String[] args) {
        Subsets sub = new Subsets();
        int[] nums = {1, 2, 3};
        System.out.println(sub.subsets(nums));
        System.out.println(sub.subsets_1(nums));
    }

}
