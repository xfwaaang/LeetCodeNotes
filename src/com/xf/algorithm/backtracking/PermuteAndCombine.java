package com.xf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xfwaaang
 * @create 2019-05-30 19:25
 *
 * 排列与组合
 * 排列的定义：
 * 从n个不同元素中，任取m(m≤n,m与n均为自然数,下同）个元素按照一定的顺序排成一列，叫做从n个不同元素中取出m个元素的一个排列；
 * 从n个不同元素中取出m(m≤n）个元素的所有排列的个数，叫做从n个不同元素中取出m个元素的排列数，用符号 A(n,m）表示。
 * 排列数目：
 * A(n, m) = n * (n-1) * (n-2) * ... * (n-m+1) = n! / (n-m)!
 * 组合的定义：
 * 从n个不同元素中，任取m(m≤n）个元素并成一组，叫做从n个不同元素中取出m个元素的一个组合；
 * 从n个不同元素中取出m(m≤n）个元素的所有组合的个数，叫做从n个不同元素中取出m个元素的组合数。用符号 C(n,m) 表示。
 * 组合数目：
 * C(n, m) = A(n, m) / A(m, m) = n! / m!*(n-m)! = n * (n-1) * (n-2) * ... * (n-m+1) / m * (m-1) *...* 1
 */
public class PermuteAndCombine {
    /**
     * 排列
     * 方法1
     * 先求出nums的m组合，再逐个求其m排列
     * @param nums 待排列数组
     * @param m 排列元素数目
     * @return 返回数组m个元素的所有排列
     */
    public static List<List<Integer>> permute(int[] nums, int m){
        List<List<Integer>> lists = new ArrayList<>();
//        先求得nums的m组合
        List<List<Integer>> cLists = combine(nums, m);

//        对每个组合进行全排列
        for (List<Integer> cList : cLists) {
            List<List<Integer>> pLists = permute(cList);
            for (List<Integer> pList : pLists)  lists.add(pList);
        }

        return lists;
    }

    /**
     * 排列
     * 方法2
     * todo
     * @param nums
     * @param m
     * @return
     */
    public static List<List<Integer>> permute2(int[] nums, int m){
        List<List<Integer>> lists = new ArrayList<>();

        return lists;
    }

    private static void permute2(int[] nums, int m, int k, int pos, List<List<Integer>> lists){

    }

    /**
     * 全排列
     * 待排列数组无重复
     * @param nums 待排列数组
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums)    list.add(num);

        permute(0, list, lists);

        return lists;
    }

    /**
     * 全排列
     * @param list 待排列数组列表
     * @return
     */
    public static List<List<Integer>> permute(List<Integer> list){
        List<List<Integer>> lists = new ArrayList<>();
        permute(0, list, lists);
        return lists;
    }

    /**
     * 全排列
     * @param pos 数组起始位置
     * @param list 待排列数组列表
     * @param lists
     */
    private static void permute(int pos, List<Integer> list, List<List<Integer>> lists) {
        if (pos == list.size()-1){
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = pos; i<list.size(); i++){
            Collections.swap(list, i, pos);
            permute(pos+1, list, lists);
            Collections.swap(list, i, pos);
        }
    }

    /**
     * 组合
     * 方法1
     * 标记法
     * 标记当前元素的状态，选中与不选中
     * 标记法时间复杂度较低
     * @param nums 待排列数组
     * @param m 排列元素数目
     * @return 返回数组m个元素的所有组合
     * 标记法
     */
    public static List<List<Integer>> combine(int[] nums, int m){
        List<List<Integer>> lists = new ArrayList<>();
        boolean visited[] = new boolean[nums.length];

        combine(nums, m, 0, 0, visited, lists);

        return lists;
    }

    /**
     * 标记法
     * @param pos 数组起始位置
     * @param k 记录选中元素的个数
     * @param visited 记录元素是否被选中
     * @param nums 待组合数组
     * @param m 组合元素个数
     * @param lists 保存组合数组的list
     */
    private static void combine(int[] nums, int m, int pos, int k, boolean[] visited, List<List<Integer>> lists) {
        if (k == m){
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++)   if (visited[i])    list.add(nums[i]);
            lists.add(list);
            return;
        }

        if (pos == nums.length)    return;

//        若元素未被选中
        if (!visited[pos]){
//            选中该元素
            visited[pos] = true;
//            继续组合剩余元素
            combine(nums, m, pos+1, k+1, visited, lists);
//            重置选中状态
            visited[pos] = false;
        }

//        不选当前元素，继续对剩余元素进行组合
        combine(nums, m, pos+1, k, visited, lists);
    }

    /**
     * 组合
     * 方法2
     * 非标记法
     * 使用数组记录当前得到的组合
     * 不需重置数组
     * @param nums
     * @param m
     * @return
     */
    public static List<List<Integer>> combine2(int[] nums, int m){
        List<List<Integer>> lists = new ArrayList<>();
        Integer[] list = new Integer[m];

        combine2(nums, m, 0, 0, list, lists);

        return lists;
    }

    /**
     * @param nums 待组合数组
     * @param m 组合元素数目
     * @param k 当前组合数组中元素数目
     * @param pos nums中元素位置
     * @param list 辅助数组，保存当前组合元素
     * @param lists 保存组合数组的list
     */
    private static void combine2(int[] nums, int m, int k, int pos, Integer[] list, List<List<Integer>> lists) {
        if (m == k){
            lists.add(new ArrayList<>(Arrays.asList(list)));
            return;
        }

        if (k > m)  return;

        if (pos == nums.length)    return;

//        不选当前元素，继续求剩余元素的组合
        combine2(nums, m, k, pos+1, list, lists);
//        选当前元素，并继续求剩余元素组合
        list[k] = nums[pos];
        combine2(nums, m, k+1, pos+1, list, lists);
    }

    /**
     * 组合
     * 方法3
     * 非标记法
     * 使用列表记录当前得到的组合
     * 需重置列表
     * @param nums
     * @param m
     * @return
     */
    public static List<List<Integer>> combine3(int[] nums, int m){
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        combine3(nums, m, 0, 0, list, lists);

        return lists;
    }

    /**
     * @param nums 待组合数组
     * @param m 组合元素数目
     * @param k 当前组合数组中元素数目
     * @param pos nums中元素位置
     * @param list 辅助数组，保存当前组合元素
     * @param lists 保存组合数组的list
     */
    private static void combine3(int[] nums, int m, int k, int pos, List<Integer> list, List<List<Integer>> lists) {
        if (m == k){
            lists.add(new ArrayList<>(list));
            return;
        }

        if (k > m)  return;

        if (pos == nums.length)    return;

//        不选当前元素，继续求剩余元素的组合
        combine3(nums, m, k, pos+1, list, lists);
//        选当前元素，并继续求剩余元素组合
        list.add(nums[pos]);
        combine3(nums, m, k+1, pos+1, list, lists);
//        重置列表，去除刚才选中的元素
        list.remove((Integer)nums[pos]);
    }


}
