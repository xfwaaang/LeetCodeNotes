package com.xf.backtracking;

import java.util.*;

/**
 * @author xfwaaang
 * @create 2019-05-27 17:20
 * 回溯算法
 * 注意：
 * 1. 使用辅助列表进行添加时，因为对象参数传递是引用传递，一般需要重置列表；
 *    而使用基本类型（值传递）、字符串类型(每次修改重新创建对象，+=)则无需考虑
 *
 * ***************************************
 * 17. Letter Combinations of a Phone Number
 *   电话键盘数字字母映射排列  todo
 * 22. Generate Parentheses
 *   括号排列，pass
 * 39. Combination Sum
 *   求数组（无重复）和为target的所有组合，数组中元素可以重复取  pass  todo improve
 * 46. Permutations
 *   全排列，原数组无重复元素  pass
 * 47. Permutations II
 *   全排列，原数组可能有重复元素  todo
 * 77. Combinations
 *   求n个数的k组合  pass
 * 78. Subsets
 *   子集问题：求集合的所有子集  pass
 * 89. Gray Code
 *   格雷码 todo
 * 90. Subsets II
 *   子集问题，原集合有重复元素  todo new
 * 216. Combination Sum III
 *   求数字1-9和为n的k组合  pass
 * 401. Binary Watch
 *   二进制时钟，类似组合问题  pass   todo new
 * 526. Beautiful Arrangement
 *   漂亮的排列，类似排列问题  pass
 * 784. Letter Case Permutation
 *   字母大小排列，改变字母的大小生成一个新的排列  pass
 * ***************************************
 */
public class Solution {
    /**
     * 17. Letter Combinations of a Phone Number
     * Medium
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     * Example:
     * Input: "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        return null;
    }

    char digitLetterMap[][] = {
            {'a', 'b', 'c'},  // 2
            {'e', 'f', 'g'},  // 3
            {'h', 'i', 'j'},  // 4
            {'k', 'l', 'm'},  // 5
            {'n', 'o', 'p'},  // 6
            {'q', 'r', 's'},  // 7
            {'t', 'u', 'v'},  // 8
            {'w', 'x', 'y', 'z'},  // 9
    };

    /**
     * 22. Generate Parentheses
     * Medium
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * For example, given n = 3, a solution set is:
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     * pass  95%  99%
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        String s = "";
        generateParenthesisHelper(n, 0, 0, s, list);
        return list;
    }

    /**
     * @param n
     * @param kl 左括号个数
     * @param kr 右括号个数
     * @param s 当前括号排列字符串
     * @param list
     */
    private void generateParenthesisHelper(int n, int kl, int kr, String s, List<String> list) {
        if (kr == n && kl == n){
            list.add(s);
            return;
        }

        if (kl > n || kr > n)   return;

//        无论什么情况都可以加左括号
        s += "(";
        generateParenthesisHelper(n, kl+1, kr, s, list);

//        仅当左括号个数大于右括号个数，才可以加右括号
        if (kl > kr){
            s = s.substring(0, s.length()-1);
            s += ")";
            generateParenthesisHelper(n, kl, kr+1, s, list);
        }

    }

    /**
     * 39. Combination Sum
     * Medium
     * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sums to target.
     * The same repeated number may be chosen from candidates unlimited number of times.
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Example 1:
     * Input: candidates = [2,3,6,7], target = 7,
     * A solution set is:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * Example 2:
     * Input: candidates = [2,3,5], target = 8,
     * A solution set is:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     * pass  6%  68%
     * todo improve
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> lists = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        combinationSumHelper(candidates, target, 0, 0, 0, list, lists);
        return new ArrayList<>(lists);
    }

    /**
     * @param candidates
     * @param target
     * @param pos 当前元素位置
     * @param k 记录某个元素重复次数
     * @param sum 当前组合的元素和
     * @param list
     * @param lists
     */
    private void combinationSumHelper(int[] candidates, int target, int pos, int k, int sum, List<Integer> list, Set<List<Integer>> lists) {
        if (sum == target){
            lists.add(new ArrayList<>(list));
            return;
        }else if (sum > target)    return;

        if (pos == candidates.length)   return;
        if (k == target)    return;

//        pos更新，k一定要置零；pos不更新，k加一
        combinationSumHelper(candidates, target, pos+1, 0, sum, list, lists);
        list.add(candidates[pos]);
        combinationSumHelper(candidates, target, pos, k+1, sum+candidates[pos], list, lists);
        combinationSumHelper(candidates, target, pos+1, 0, sum+candidates[pos], list, lists);
        list.remove((Integer)candidates[pos]);
    }

    /**
     * 40. Combination Sum II
     * Medium
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sums to target.
     * Each number in candidates may only be used once in the combination.
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Example 1:
     * Input: candidates = [10,1,2,7,6,1,5], target = 8,
     * A solution set is:
     * [
     *   [1, 7],
     *   [1, 2, 5],
     *   [2, 6],
     *   [1, 1, 6]
     * ]
     * Example 2:
     * Input: candidates = [2,5,2,1,2], target = 5,
     * A solution set is:
     * [
     *   [1,2,2],
     *   [5]
     * ]
     * todo
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> lists = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2Helper(candidates, target, 0, 0, list, lists);
        return new ArrayList<>(lists);
    }

    private void combinationSum2Helper(int[] candidates, int target, int pos, int sum, List<Integer> list, Set<List<Integer>> lists) {
        if (sum == target){
            lists.add(new ArrayList<>(list));
            return;
        }

        if (pos == candidates.length)   return;

        combinationSum2Helper(candidates, target, pos+1, sum, list, lists);
        list.add(candidates[pos]);
        combinationSum2Helper(candidates, target, pos+1, sum + candidates[pos], list, lists);
        list.remove((Integer)candidates[pos]);
    }

    /**
     * 46. Permutations
     * Medium
     * Given a collection of distinct integers, return all possible permutations.
     * Example:
     * Input: [1,2,3]
     * Output:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     * pass  99%  96%
     * 以4个自然数为例{0,1,2,3}
     * 1. 0开头，其后是{1,2,3}的各种排列
     * 2. 1开头，其后是{0,2,3}的各种排列 依次类推，以2开头、以3开头
     * 此问题的实质是求解比原始问题少一个数的排列，这是一个同类子问题，可以用递归算法求解
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums)    list.add(num);
        permuteHelper(0, list, lists);
        return lists;
    }

    private void permuteHelper(int pos, List<Integer> list, List<List<Integer>> lists) {
        if (pos == list.size()-1){
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i<list.size(); i++){
            Collections.swap(list, i, pos);
            permuteHelper(pos+1, list, lists);
            Collections.swap(list, i, pos);
        }
    }

    /**
     * 47. Permutations II
     * Medium
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
     * Example:
     * Input: [1,1,2]
     * Output:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     * todo
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums)    list.add(num);
        permuteUniqueHelper(0, list, lists);
        return lists;
    }

//    todo
    private void permuteUniqueHelper(int pos, List<Integer> list, List<List<Integer>> lists) {
        if (pos == list.size()-1){
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i < list.size(); i++) {
            if (i != pos && list.get(i) == list.get(pos))   continue;

            Collections.swap(list, i, pos);
            permuteUniqueHelper(pos+1, list, lists);
            Collections.swap(list, i, pos);
        }
    }

    /**
     * 77. Combinations
     * Medium
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * Example:
     * Input: n = 4, k = 2
     * Output:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     * pass  83%  71%
     * 标记法
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        boolean visited[] = new boolean[n];
        combineHelper(n, k, 0, 0, visited, lists);
        return lists;
    }

    private void combineHelper(int n, int k, int pos, int m, boolean[] visited, List<List<Integer>> lists) {
        if (m == k){
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i])   list.add(i+1);
            }
            lists.add(list);
            return;
        }

        if (pos == n)   return;

        if (!visited[pos]){
            visited[pos] = true;
            combineHelper(n, k, pos+1, m+1, visited,lists);
            visited[pos] = false;
        }

        combineHelper(n, k, pos+1, m, visited, lists);
    }

    /**
     * 77. Combinations
     * pass  55% 79%
     * 非标记法
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combine2Helper(n, k, 0, 0, list, lists);
        return lists;
    }

    private void combine2Helper(int n, int k, int pos, int m, List<Integer> list, List<List<Integer>> lists) {
        if (m == k){
            lists.add(new ArrayList<>(list));
            return;
        }

        if (pos == n)   return;

        combine2Helper(n, k, pos+1, m, list, lists);
        list.add(pos+1);
        combine2Helper(n, k, pos+1, m+1, list, lists);
        list.remove((Integer)(pos+1));
    }

    /**
     * 78. Subsets
     * Medium
     * Given a set of distinct integers, nums, return all possible subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     * Example:
     * Input: nums = [1,2,3]
     * Output:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     * 递归实现，两种方法
     * pass
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
//        标记法
//        boolean[] visited = new boolean[nums.length];
//        subsetsHelper(nums, 0, visited, lists);

//        非标记法
        List<Integer> list = new ArrayList<>();
        subsetsHelper2(nums, 0, list, lists);

        return lists;
    }

    /**
     * pass  68%  99%
     * 非标记法，类似组合算法的实现
     * @param nums
     * @param pos
     * @param list
     * @param lists
     */
    private void subsetsHelper2(int[] nums, int pos, List<Integer> list, List<List<Integer>> lists) {
        if (pos == nums.length){
            lists.add(new ArrayList<>(list));
            return;
        }

        subsetsHelper2(nums, pos+1, list, lists);
        list.add(nums[pos]);
        subsetsHelper2(nums, pos+1, list, lists);
        list.remove((Integer)nums[pos]);
    }

    /**
     * pass  100%  99%
     * 标记法，类似组合算法的实现
     * @param nums 原数组
     * @param pos 当前元素位置
     * @param visited 记录元素的选中状态
     * @param lists 辅助列表，存储结果
     */
    private void subsetsHelper(int[] nums, int pos, boolean[] visited, List<List<Integer>> lists) {
        if (pos == nums.length){
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++)   if (visited[i])    list.add(nums[i]);
            lists.add(list);
            return;
        }

        if (!visited[pos]){
//            若pos未被选中，则选中pos
            visited[pos] = true;
            subsetsHelper(nums, pos+1, visited, lists);
//            重置pos的选中状态
            visited[pos] = false;
        }

//        不选pos元素
        subsetsHelper(nums, pos+1, visited, lists);
    }

    /**
     * 78. Subsets
     * 迭代实现
     * pass  100% 99%
     * 初始，lists为{{}}
     * 遍历nums，每遍历一个元素，该元素与与前面每一个子集均可形成一个新的子集
     * 依次添加前n个子集到lists中，并将当前元素添加到刚才添加进的子集
     * lists列表的情况为：
     * {{}}
     *     {{},{}} -> {{},{1}}
     * {{},{1}}
     *     {{},{1},{}} -> {{},{1},{2}}
     *     {{},{1},{2},{1}} -> {{},{1},{2},{1,2}}
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums){
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());

        for (int num : nums) {
            int n = lists.size();
            for (int i = 0; i < n; i++) {
                lists.add(new ArrayList<>(lists.get(i)));
                lists.get(lists.size()-1).add(num);
            }
        }

        return lists;
    }

    /**
     * 89. Gray Code
     * Medium
     * The gray code is a binary numeral system where two successive values differ in only one bit.
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
     * Example 1:
     * Input: 2
     * Output: [0,1,3,2]
     * Explanation:
     * 00 - 0
     * 01 - 1
     * 11 - 3
     * 10 - 2
     * For a given n, a gray code sequence may not be uniquely defined.
     * For example, [0,2,3,1] is also a valid gray code sequence.
     * 00 - 0
     * 10 - 2
     * 11 - 3
     * 01 - 1
     * Example 2:
     * Input: 0
     * Output: [0]
     * Explanation: We define the gray code sequence to begin with 0.
     *              A gray code sequence of n has size = 2n, which for n = 0 the size is 20 = 1.
     *              Therefore, for n = 0 the gray code sequence is [0].
     * 格雷码：以零开头，每相邻数字仅有一位二进制不同
     * todo
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        return null;
    }

    /**
     * 90. Subsets II
     * Medium
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     * Example:
     * Input: [1,2,2]
     * Output:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     * pass  16%  99%
     * todo new
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> lists = new HashSet<>();
        boolean visited[] = new boolean[nums.length];
        Arrays.sort(nums);
        subsetsWithDupHelper(nums, 0, visited, lists);
        return new ArrayList<>(lists);
    }

    private void subsetsWithDupHelper(int[] nums, int pos, boolean[] visited, Set<List<Integer>> lists) {
        if (pos == nums.length){
            List<Integer> list = new ArrayList<>();
            for (int i=0; i<nums.length; i++)   if (visited[i])    list.add(nums[i]);
            lists.add(list);
            return;
        }

        if (!visited[pos]){
            visited[pos] = true;
            subsetsWithDupHelper(nums, pos+1, visited, lists);
            visited[pos] = false;
        }

        subsetsWithDupHelper(nums, pos+1, visited, lists);
    }

    /**
     * 216. Combination Sum III
     * Medium
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     * Note:
     * All numbers will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Example 1:
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     * Example 2:
     * Input: k = 3, n = 9
     * Output: [[1,2,6], [1,3,5], [2,3,4]]
     * pass  41%  31%
     * 方法1
     * 使用组合问题的非标记法
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        combinationSum3Helper(k, n, 0, 0, list, lists);
        return lists;
    }

    /**
     * @param k
     * @param n
     * @param m 当前组合的元素个数
     * @param pos 当前元素的位置
     * @param list
     * @param lists
     */
    private void combinationSum3Helper(int k, int n, int m, int pos, List<Integer> list, List<List<Integer>> lists) {
        if (m == k){
            int sum = 0;
            for (Integer i : list)    sum += i;
            if (sum == n)   lists.add(new ArrayList<>(list));
            return;
        }

        if (pos == 9)   return;

        combinationSum3Helper(k, n, m, pos+1, list, lists);
        list.add(pos+1);
        combinationSum3Helper(k, n, m+1, pos+1, list, lists);
        list.remove((Integer)(pos+1));
    }

    /**
     * @param k
     * @param n
     * @param m
     * @param pos
     * @param sum 当前组合的所有元素和
     * @param list
     * @param lists
     */
    private void combinationSum3Helper2(int k, int n, int m, int pos, int sum, List<Integer> list, List<List<Integer>> lists){
        if (m == k){
            if (sum == n)   lists.add(new ArrayList<>(list));
            return;
        }else {
            if (sum >= n)   return;
        }

        if (pos == 9)   return;

        combinationSum3Helper2(k, n, m, pos+1, sum, list, lists);
        list.add(pos+1);
        combinationSum3Helper2(k, n, m+1, pos+1, sum + pos + 1, list, lists);
        list.remove((Integer)(pos+1));
    }

    /**
     * 216. Combination Sum III
     * Medium
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     * Note:
     * All numbers will be positive integers.
     * The solution set must not contain duplicate combinations.
     * Example 1:
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     * Example 2:
     * Input: k = 3, n = 9
     * Output: [[1,2,6], [1,3,5], [2,3,4]]
     * pass  41%  31%
     * 方法2
     * 使用组合问题的标记法
     * pass  41%  30%
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum32(int k, int n) {
        List<List<Integer>> lists = new ArrayList<>();
        boolean visited[] = new boolean[9];
        combinationSum32Helper(k, n, 0, 0, visited, lists);
        return lists;
    }

    /**
     * @param k
     * @param n
     * @param m 当前组合的元素个数
     * @param pos 当前元素的位置
     * @param visited 记录元素的访问状态
     * @param lists
     */
    private void combinationSum32Helper(int k, int n, int m, int pos, boolean[] visited, List<List<Integer>> lists) {
        if (m == k){
            int sum = 0;
            List<Integer> list = new ArrayList<>();
            for (int i=0; i<visited.length; i++){
                if (visited[i]) {
                    list.add(i + 1);
                    sum += i + 1;
                }
            }
            if (sum == n)   lists.add(list);
            return;
        }

        if (pos == 9)   return;

        if (!visited[pos]){
            visited[pos] = true;
            combinationSum32Helper(k, n, m+1, pos+1, visited, lists);
            visited[pos] = false;
        }

        combinationSum32Helper(k, n, m, pos+1, visited, lists);
    }

    /**
     * 357. Count Numbers with Unique Digits
     * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
     * Example:
     * Input: 2
     * Output: 91
     * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
     *              excluding 11,22,33,44,55,66,77,88,99
     * todo
     * @param n
     * @return
     */
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)    return 1;

        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int count = 0;
        int count1 = 0;
        for (int k=1; k<=n; k++){
            List<List<Integer>> kLists = PermuteAndCombine.permute(nums, k);
            List<List<Integer>> kLists1 = PermuteAndCombine.permute(nums1, k);
            count += kLists.size() - count1;
            count1 = kLists1.size();
        }
        return count;
    }

    private int[] hours = {1, 2, 4, 8};
    private int[] minutes = {1, 2, 4, 8, 16, 32};

    /**
     * 401. Binary Watch
     * Easy
     * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
     * Each LED represents a zero or one, with the least significant bit on the right.
     * For example, the above binary watch reads "3:25".
     * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
     * Example:
     * Input: n = 1
     * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
     * Note:
     * The order of output does not matter.
     * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
     * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
     * pass  94%  100%
     * 分别求hours和minutes的组合
     * 遍历所有情况
     * @param num
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= Math.min(hours.length, num); i++) {
//            求hours的i组合，minutes的num-i组合
            List<List<Integer>> hourCmbLists = PermuteAndCombine.combine(hours, i);
            List<List<Integer>> minuteCmbLists = PermuteAndCombine.combine(minutes, num-i);
//            遍历所有情况
            for (List<Integer> hourCmbList : hourCmbLists) {
                int hour = 0;
                for (Integer h : hourCmbList)   hour += h;

                if (hour >= 12) continue;

                for (List<Integer> minuteCmbList : minuteCmbLists) {
                    int minute = 0;
                    for (Integer m : minuteCmbList)    minute += m;

                    if (minute >= 60)   continue;

                    String mnt = minute < 10 ? "0" + minute : "" + minute;
                    String time = "" + hour + ":" + mnt;

                    list.add(time);
                }
            }
        }

        return list;
    }

    /**
     * todo new
     * @param num
     * @return
     */
    public List<String> readBinaryWatch2(int num){
        List<String> list = new ArrayList<>();
        boolean hVisited[] = new boolean[hours.length];
        boolean mVisited[] = new boolean[minutes.length];
        readBinaryWatch2Helper(num, 0, 0, 0, 0, hVisited, mVisited, list);
        return list;
    }

//    todo
    private void readBinaryWatch2Helper(int num, int posh, int posm, int cnth, int cntm, boolean[] hVisited, boolean[] mVisited, List<String> list) {
        if (num == cnth + cntm){
            int hour = 0, minute = 0;
            for (int i = 0; i < hours.length; i++)   if (hVisited[i])   hour += hours[i];
            for (int i = 0; i < minutes.length; i++)   if (mVisited[i])   minute += minutes[i];
            if (hour >= 12)    return;
            if (minute >= 60)   return;
            String mnt = minute < 10 ? "0" + minute : "" + minute;
            String time = "" + hour + ":" + mnt;
            list.add(time);
            return;
        }

        if (posh == hours.length)   return;
        if (posm == minutes.length)    return;

        if (!hVisited[posh]){
            hVisited[posh] = true;
            readBinaryWatch2Helper(num, posh+1, posm, cnth+1, cntm, hVisited, mVisited, list);
            hVisited[posh] = false;
        }


        if (!mVisited[posm]){
            mVisited[posm] = true;
            readBinaryWatch2Helper(num, posh, posm+1, cnth, cntm+1, hVisited, mVisited, list);
            mVisited[posm] = false;
        }

        readBinaryWatch2Helper(num, posh+1, posm, cnth, cntm, hVisited, mVisited, list);

        readBinaryWatch2Helper(num, posh, posm+1, cnth, cntm, hVisited, mVisited, list);

    }

    /**
     * 526. Beautiful Arrangement
     * Medium
     * Suppose you have N integers from 1 to N.
     * We define a beautiful arrangement as an array that is constructed by these N numbers successfully
     * if one of the following is true for the ith position (1 <= i <= N) in this array:
     * The number at the ith position is divisible by i.
     * i is divisible by the number at the ith position.
     * Now given N, how many beautiful arrangements can you construct?
     * Example 1:
     * Input: 2
     * Output: 2
     * Explanation:
     * The first beautiful arrangement is [1, 2]:
     * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
     * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
     * The second beautiful arrangement is [2, 1]:
     * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
     * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
     * Note:
     * N is a positive integer and will not exceed 15.
     * pass  77%  62%
     * 借鉴全排列的思路
     * 每次交换时，考虑交换后pos位置的元素是否满足要求，若满足则交换，否则不交换
     * 上述操作执行完，前n-1个元素已满足要求
     * 当pos到达最后一个元素时，判断pos元素是否满足要求，若满足则count加1
     * @param N
     * @return
     */
    public int countArrangement(int N) {
        List<Integer> list = new ArrayList<>();
        for (int i=1; i<=N; i++)    list.add(i);
        countArrangementHelper(list, 0);
        return count;
    }

    int count = 0;

    private void countArrangementHelper(List<Integer> list, int pos) {
        if (pos == list.size()-1){
            if ((pos+1) % list.get(pos) == 0 || list.get(pos) % (pos+1) == 0)   count++;
            return;
        }

        for (int i = pos; i < list.size(); i++) {
            if ((pos+1) % list.get(i) == 0 || list.get(i) % (pos+1) == 0){
                Collections.swap(list, i, pos);
                countArrangementHelper(list, pos+1);
                Collections.swap(list, i, pos);
            }
        }

    }

    /**
     * 784. Letter Case Permutation
     * https://leetcode.com/problems/letter-case-permutation/
     * Easy
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     * Return a list of all possible strings we could create.
     * Examples:
     * Input: S = "a1b2"
     * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
     * Input: S = "3z4"
     * Output: ["3z4", "3Z4"]
     * Input: S = "12345"
     * Output: ["12345"]
     * Note:
     * S will be a string with length between 1 and 12.
     * S will consist only of letters or digits.
     *  pass  100%  99%
     *  对于s[i]有两种情况
     *  1. 不进行转换
     *  2. 进行转换，条件是为字母字符
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList();
        letterCasePermutationHelper(S.toCharArray(), 0, list);
        return list;
    }

    private void letterCasePermutationHelper(char[] s, int i, List<String> list) {
        if (i == s.length){
            list.add(new String(s));
            return;
        }
        letterCasePermutationHelper(s, i+1, list);
        if (Character.isLetter(s[i])){
            s[i] ^= (1 << 5);   // toggle letter case
            letterCasePermutationHelper(s, i+1, list);
        }
    }


}
