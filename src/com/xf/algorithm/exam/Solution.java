package com.xf.algorithm.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xfwaaang
 * @create 2019-09-04 21:38
 */
public class Solution {
    /**
     * 表达式解析
     * 括号表示将里面的字符串反转
     * 输入：一行字符串
     * 输出：一行字符串、若括号不匹配，输出空字符串
     * input：((ur)oi)
     * output：iour
     * @param str
     * @return
     */
    public static String parseEX(String str){
        char[] chs = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        List<Character> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : chs) {
            if(ch == ')'){
                list.clear();
                char c = ' ';
                while (!stack.empty()){
                    c = stack.pop();
                    if(c == '(')    break;
                    list.add(c);
                }
                if(c != '('){
                    return "";
                }
                if(stack.empty()){
                    for (Character character : list) {
                        sb.append(character);
                    }
                }else{
                    for (Character character : list) {
                        stack.push(character);
                    }
                }
            }else if(ch == '('){
                stack.push(ch);
            }else{
                if(!stack.empty()){
                    stack.push(ch);
                }else {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }

    /**
     * 输入：两行
     * 第一行：m，n分别表示节点个数和任务个数
     * 第二行：正整数序列，表示每个任务执行需要的时间
     * 输出：
     * 一个整数，表示最短完成时间
     * 注意：一个节点只能执行相邻的任务
     * input：
     * 3 5
     * 1 5 3 4 2
     * output:
     * 6
     * todo  read
     * @param m
     * @param nums
     * @return
     */
    public int scheduleTask(int m,int[] nums) {
        int l = 0, h = 0;
        for(int num : nums) {
            l = Math.max(l, num);
            h += num;
        }
        int result = Integer.MAX_VALUE;
        while(l <= h) {
            int mid = l + (h - l) / 2;
            int parts = getParts(nums, mid);
            if(parts > m)
                l = mid + 1;
            else {
                h = mid - 1;
                result = Math.min(result, mid);
            }
        }
        return result;
    }

    private int getParts(int[] nums, int target) {
        int parts = 1;
        int curSum = 0;
        for(int num : nums) {
            if(curSum + num <= target)
                curSum += num;
            else {
                curSum = num;
                parts++;
            }
        }
        return parts;
    }

    /**
     * 求数对之差的最大值
     * 数组中的一个数减去它右边子数组中的一个数可以得到一个差值，求所有可能差值的最大值
     * 如[1,4,17,3,2,9]，最大差值为17-2=15
     * 动态规划
     * @param a
     * @return
     */
    public int maxNumPairDiff(int[] a){
        int maxE = a[0];
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 1; i < a.length; i++) {
            maxDiff = Math.max(maxDiff, maxE - a[i]);
            maxE = Math.max(maxE, a[i]);
        }
        return maxDiff;
    }

    /**
     * 输入全为小写字母的字符串
     * 输出无重复字母的字符串
     * @param str
     * @return
     */
    public String unique(String str){
        StringBuilder sb = new StringBuilder();
        int[] letters = new int[26];
        for (int i = 0; i < letters.length; i++)    letters[i] = 0;
        for (char ch : str.toCharArray()) {
            if(letters[ch-'a'] == 0){
                sb.append(ch);
                letters[ch-'a'] = 1;
            }
        }
        return sb.toString();
    }

}
