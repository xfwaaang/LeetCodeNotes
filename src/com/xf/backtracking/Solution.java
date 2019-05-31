package com.xf.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xfwaaang
 * @create 2019-05-27 17:20
 * 回溯算法
 * 注意：
 * 1. 使用辅助列表进行添加时，因为对象参数传递是引用传递，一般需要重置列表；
 *    而使用基本类型（值传递）、字符串类型(每次修改重新创建对象，+=)则无需考虑
 */
public class Solution {

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

    private void permuteHelper(int k, List<Integer> list, List<List<Integer>> lists) {
        if (k == list.size()-1){
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = k; i<list.size(); i++){
            Collections.swap(list, i, k);
            permuteHelper(k+1, list, lists);
            Collections.swap(list, i, k);
        }
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
     * todo
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
     *784. Letter Case Permutation
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
