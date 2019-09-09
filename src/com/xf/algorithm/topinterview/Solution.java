package com.xf.algorithm.topinterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author xfwaaang
 * @create 2019-07-31 16:44
 * Top Interview Questions
 * 1. 最长无重复子串长度   3. Longest Substring Without Repeating Characters
 * 2. 最长回文子串    5. Longest Palindromic Substring
 */
public class Solution {
    public static void main(String[] args){

    }


    /**
     * 3. Longest Substring Without Repeating Characters
     * Medium
     * Given a string, find the length of the longest substring without repeating characters.
     * Example 1:
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     * 最长无重复子串长度
     * pass  91%  94%
     * 不停向当前无重复子串中添加新字符，添加前判断是否存在该字符
     * s[i,j]表示当前无重复字符子串
     * 若s[i,j]中含有字符s[j+1]，更新i和len
     * 否则，更新len
     * j++
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();

        if(n == 1)  return 1;

        int i = 0, j = 1;
        int len = 0;
        while(j < n)
        {
            int pos = s.substring(i, j).indexOf(s.charAt(j));
            if(pos == -1)
            {
                j++;
                len = Math.max(len, j-i);
                continue;
            }
            i = i + pos + 1;
            j++;
            len = Math.max(len, j-i);
        }

        return len;
    }

    /**
     * 5. Longest Palindromic Substring
     * Medium
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
     * Example 1:
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     * Example 2:
     * Input: "cbbd"
     * Output: "bb"
     * 最长回文子串
     * todo
     * pass  5%  98%
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        String res = "";

        return res;
    }

    private boolean isPalindrome(String s){
        int n = s.length();
        for(int i=0; i<n/2; i++)
        {
            if(s.charAt(i) != s.charAt(n-i-1))  return false;
        }
        return true;
    }

    /**
     * 7. Reverse Integer
     * Easy
     * Given a 32-bit signed integer, reverse digits of an integer.
     * Example 1:
     * Input: 123
     * Output: 321
     * Example 2:
     * Input: -123
     * Output: -321
     * Example 3:
     * Input: 120
     * Output: 21
     * Note:
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     * pass  100%  8%
     * @param x
     * @return
     */
    public int reverse(int x) {
        long ret = 0;
        while(x != 0){
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        if(ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE){
            return 0;
        }else{
            return (int)ret;
        }
    }

    /**
     * 8. String to Integer (atoi)
     * pass  100%  100%
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        if(str.length() == 0)   return 0;

        int i = 0, j;
        //过滤首部空白符
        while(i < str.length() && str.charAt(i) == ' ')    i++;

        if(i >= str.length())   return 0;

        j = i;
        boolean flag = false;   //  true为负数，false为正数
        if(str.charAt(i) == '+' || str.charAt(i) == '-'){
            if(i == str.length()-1)   return 0;
            if(i+1 < str.length() && (str.charAt(i+1) < '0' || str.charAt(i+1) > '9'))  return 0;
            if(str.charAt(i) == '-')    flag = true;
            i++;
            j++;
        }else if(str.charAt(i) < '0' || str.charAt(i) > '9'){
            return 0;
        }

        while(j < str.length() && (str.charAt(j) >= '0' && str.charAt(j) <= '9'))    j++;

        long ret = 0;
        for(int k=i; k<j; k++){
            ret = ret * 10 + str.charAt(k) - '0';
            if(ret > Integer.MAX_VALUE){
                if(flag){
                    return Integer.MIN_VALUE;
                }else{
                    return Integer.MAX_VALUE;
                }
            }
        }

        if(flag){
            return -(int)ret;
        }else{
            return (int)ret;
        }

    }

    /**
     * 10. Regular Expression Matching
     * Hard
     * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     * Note:
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like . or *.
     * Example 1:
     * Input:
     * s = "aa"
     * p = "a"
     * Output: false
     * Explanation: "a" does not match the entire string "aa".
     * Example 2:
     * Input:
     * s = "aa"
     * p = "a*"
     * Output: true
     * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
     * Example 3:
     * Input:
     * s = "ab"
     * p = ".*"
     * Output: true
     * Explanation: ".*" means "zero or more (*) of any character (.)".
     * Example 4:
     * Input:
     * s = "aab"
     * p = "c*a*b"
     * Output: true
     * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
     * Example 5:
     * Input:
     * s = "mississippi"
     * p = "mis*is*p*."
     * Output: false
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        //todo
        return true;
    }

    /**
     * 11. Container With Most Water
     * pass  100%  87%
     * 保持当前宽为最大，i、j初始指向两端，依次向中间移动两个 指针
     * 盛水面积取决于较低的边，因此若要寻找比当前面积大的情况，需要向中间移动较低边的指针，使其指向的比当前高的边
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int area = 0;
        while(i < j){
            area = Math.max(area, Math.min(height[i], height[j]) * (j - i));
            if(height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return area;
    }

    /**
     * 14. Longest Common Prefix
     * Easy
     * Write a function to find the longest common prefix string amongst an array of strings.
     * If there is no common prefix, return an empty string "".
     * Example 1:
     * Input: ["flower","flow","flight"]
     * Output: "fl"
     * Example 2:
     * Input: ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     * pass  74%  100%
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)    return "";

        int k = -1;
        boolean flag;

        while(true) {
            flag = false;
            k++;

            if(k >= strs[0].length())   break;

            for (int i = 1; i < strs.length; i++) {
                if(k >= strs[i].length()){
                    flag = true;
                    break;
                }
                if (strs[0].charAt(k) != strs[i].charAt(k)){
                    flag = true;
                    break;
                }
            }

            if(flag)    break;
        }

        return strs[0].substring(0, k);
    }

    /**
     * 15. 3Sum
     * Medium
     * Given an array nums of n integers, are there elements
     * a, b, c in nums such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     * Note:
     * The solution set must not contain duplicate triplets.
     * Example:
     * Given array nums = [-1, 0, 1, 2, -1, -4],
     * A solution set is:
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     * todo
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        int i = 0, j = nums.length-1;
        Arrays.sort(nums);
        while(i < j - 1){
            System.out.println(i + " " + j);
            int sum = nums[i] + nums[j];

            int p = i + 1, q = j - 1;
            while(p <= q){
                int m = (p + q) / 2;
                if(nums[m] > -sum){
                    q = m - 1;
                }else if(nums[m] < -sum){
                    p = m + 1;
                }else{
                    break;
                }
            }
            if(p <= q){
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(-sum);
                ret.add(list);
            }

            if(sum > 0){
                j--;
            }else if(sum < 0){
                i++;
            }else{
                while(i < j && nums[i+1] == nums[i])    i++;
                if(i+2 < j){
                    if(nums[i] + nums[j-2] + nums[j-1] == 0){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j-2]);
                        list.add(nums[j-1]);
                        ret.add(list);
                        i++;
                    }
                    if(nums[j] + nums[i+2] + nums[i+1] == 0){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[j]);
                        list.add(nums[i+2]);
                        list.add(nums[i+1]);
                        ret.add(list);
                        j--;
                    }
                }else {
                    i++;
                    j--;
                }
            }
        }
        return ret;
    }

    /**
     * 17. Letter Combinations of a Phone Number
     * Medium
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     * Example:
     * Input: "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * pass  100%  98%
     * @param digits
     * @return
     */
    String[] strs = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0)    return new ArrayList<>();

        return letterCombinationsHelper(digits, digits.length()-1);
    }

    private List<String> letterCombinationsHelper(String digits, int k) {
        List<String> list = new ArrayList<>();
        char[] chs = strs[digits.charAt(k)-'0'-2].toCharArray();

        if(k == 0){
            for(char ch : chs)   list.add(""+ch);
            return list;
        }

        List<String> tlist = letterCombinationsHelper(digits, k-1);
        for(char ch : chs)   for(String s : tlist)   list.add(s + ch);

        return list;
    }

    /**
     * 19. Remove Nth Node From End of List
     * Medium
     * Given a linked list, remove the n-th node from the end of list and return its head.
     * Example:
     * Given linked list: 1->2->3->4->5, and n = 2.
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     * Given n will always be valid.
     * pass  100%  100%
     * 方法一
     * 先求链表的结点数目
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int m = 0;
        ListNode p = head;
        while(p != null){
            m++;
            p = p.next;
        }

        p = head;
        int t = m - n;

        if(t == 0){
            head = head.next;
            return head;
        }

        while(t-- > 1)  p = p.next;
        p.next = p.next.next;

        return head;
    }

    /**
     * 19. Remove Nth Node From End of List
     * 方法二
     * 设置两个指针，让第二个指针先走n步，然后一起走到直到第二个指针指向末尾
     * 此时第一个指针正好走到待删除结点的前驱结点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode fst = head, scd = head;
        while(n-- > 0)  scd = scd.next;
        if(scd == null){
            head = head.next;
            return head;
        }
        while(scd.next != null){
            fst = fst.next;
            scd = scd.next;
        }
        fst.next = fst.next.next;
        return head;
    }

    /**
     * 20. Valid Parentheses
     * Easy
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * An input string is valid if:
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Note that an empty string is also considered valid.
     * Example 1:
     * Input: "()"
     * Output: true
     * Example 2:
     * Input: "()[]{}"
     * Output: true
     * 判断括号序列是否有效
     * 使用栈
     * pass  98%  100%
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){
            switch (ch){
                case '(':
                case '[':
                case '{':
                    stack.push(ch);
                    break;
                case ')':
                case ']':
                case '}':
                    if(stack.empty())   return false;
                    int d = ch - stack.pop();
                    if(!(d == 1 || d == 2))    return false;
                    break;
            }

        }

        if(!stack.empty())   return false;

        return true;
    }

    /**
     * 26. Remove Duplicates from Sorted Array
     * Easy
     * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     * Example 1:
     * Given nums = [1,1,2],
     * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
     * It doesn't matter what you leave beyond the returned length.
     * 删除数组中重复值，返回无重复数字的长度
     * 采用双指针
     * pass  97%  99%
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0)    return 0;

        int i = 0;
        for(int j=1; j<nums.length; j++){
            if (nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }

    /**
     * 28. Implement strStr()
     * Easy
     * Implement strStr().
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * Example 1:
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     * Example 2:
     * Input: haystack = "aaaaa", needle = "bba"
     * Output: -1
     * 常规匹配，pass  52%  97%
     * KMP，pass  52%  97%
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int i = 0, j = 0;
        char[] t = haystack.toCharArray();
        char[] p = needle.toCharArray();

        int[] next = getNext(needle);

        while (i < t.length && j < p.length){
            if(j == -1 || t[i] == p[j]){
                i++;
                j++;
            }else{
                //常规
//                i = i - j + 1;
//                j = 0;
                //KMP
                j = next[j];
            }
        }

        if(j == p.length)    return i - j;

        return -1;
    }

    //根据模式串的最长相同前缀后缀求next数组
    public int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        if(p.length == 0)   return next;
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            //p[k]表示前缀，p[j]表示后缀
            if (k == -1 || p[k] == p[j]) {
                next[++j] = ++k;//即当p[k] == p[j]时，next[j+1] == next[j] + 1=k+1
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 29. Divide Two Integers
     * Medium
     * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
     * Return the quotient after dividing dividend by divisor.
     * The integer division should truncate toward zero.
     * Example 1:
     * Input: dividend = 10, divisor = 3
     * Output: 3
     * Example 2:
     * Input: dividend = 7, divisor = -3
     * Output: -2
     * 不用操作符实现整除
     * pass  100%  6%
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (divisor == 1) return dividend;
        if (divisor == -1) return -dividend;

        long div = Math.abs((long)dividend);
        long divs = Math.abs((long)divisor);
        int result = 0;
        while (div >= divs) {
            long tmp = divs;
            int count = 1;
            while (tmp + tmp < div) {
                tmp += tmp;
                count += count;
            }
            div -= tmp;
            result += count;
        }
        return ((dividend ^ divisor)  < 0)? -result: result;
    }

    /**
     * 33. Search in Rotated Sorted Array
     * Medium
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * You may assume no duplicate exists in the array.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * Example 1:
     * Input: nums = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     * Example 2:
     * Input: nums = [4,5,6,7,0,1,2], target = 3
     * Output: -1
     * pass  100%  100%
     * 查找有序旋转数组中的元素
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums.length == 0)    return -1;

        int i = 0, j = nums.length - 1;
        while (i <= j){
            int m = (i + j) / 2;
            if (nums[m] > target){
                // 分两种情况，m分别在左右子数组
                if (nums[i] > nums[m]){
                    j = m - 1;
                }else {
                    if (target >= nums[i]){
                        j = m -1;
                    }else {
                        i = m + 1;
                    }
                }
            }else if (nums[m] < target){
                if (nums[i] > nums[m]){
                    if (target <= nums[nums.length-1]){
                        i = m + 1;
                    }else {
                        j = m - 1;
                    }
                }else {
                    i = m + 1;
                }
            }else {
                return m;
            }
        }

        return -1;
    }

    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * Medium
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * If the target is not found in the array, return [-1, -1].
     * Example 1:
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * Example 2:
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     * pass 100%  100%
     * 查找元素最先出现和最后出现的位置
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ret = {-1, -1};

        int i = searchRangeHelper(nums, target, true);

        if (i == -1){
            return ret;
        }

        ret[0] = i;
        ret[1] = searchRangeHelper(nums, target, false);

        return ret;
    }

    /**
     * 查找元素最先出现或最后出现的位置
     * 改进二分查找
     * @param nums
     * @param target
     * @param flag  true：最先出现的位置，false：最后出现的位置
     * @return  -1：没找到
     */
    private int searchRangeHelper(int[] nums, int target, boolean flag){
        int i = 0, j = nums.length;

        while (i < j){
            int m = i + (j - i) / 2;
            if (nums[m] > target || (flag && nums[m] == target)){
                j = m;
            }else {
                i = m + 1;
            }
        }

        if (!flag){
            i -= 1;
        }

        if (i == nums.length || nums[i] != target){
            return -1;
        }

        return i;
    }

    /**
     * 41. First Missing Positive
     * Hard
     * Given an unsorted integer array, find the smallest missing positive integer.
     * Example 1:
     * Input: [1,2,0]
     * Output: 3
     * Example 2:
     * Input: [3,4,-1,1]
     * Output: 2
     * Example 3:
     * Input: [7,8,9,11,12]
     * Output: 1
     * Note:
     * Your algorithm should run in O(n) time and uses constant extra space.
     * pass  100%  100%
     * time:O(k*n)  space:O(1)
     * 第一个缺失的最小正数
     * todo time:O(n)
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int k = 1;

        while (true){
            int i = 0;
            for (; i<nums.length; i++)    if (nums[i] == k)   break;
            if (i == nums.length)   break;
            k++;
        }

        return k;
    }

    /**
     * 42. Trapping Rain Water
     * Hard
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
     * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
     * Example:
     * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
     * Output: 6
     * 最多存水量
     * pass  98%  98%
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int i =0, j = 0;
        int area = 0;

        while (i < height.length){
            //寻找当前第一个右下坡，i指向坡顶
            while (i+1 < height.length && height[i+1] >= height[i])   i++;

            //寻找第一个高于等于height[i]的h
            int k = height[i];
            while (true){
                if (k == 0)    break;
                j = i + 2;
                while (j < height.length && height[j] < k) j++;
                if (j < height.length)  break;
                k--;
            }

            if (k == 0)    break;

            area += k * (j - i -1);
            for (int x=i+1; x<j; x++)   area -= Math.min(height[x], height[j]);

            i = j;
        }

        return area;
    }

    /**
     * 42. Trapping Rain Water
     * trap1,2,3都是计算每个柱子上的存水量，然后累加
     * brute force
     * pass  6%  5%
     * 计算每一个柱子上的存水量，累加
     * time:O(n^2)  space:O(1)
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int area = 0;
        int max_left, max_right;

        for (int i = 1; i < height.length-1; i++) {
            max_left = height[i];
            max_right = height[i];

            for (int j=i-1; j>=0; j--)     max_left = Math.max(max_left, height[j]);
            for (int j=i+1; j<height.length; j++)   max_right = Math.max(max_right, height[j]);

            area += Math.min(max_left, max_right) - height[i];
        }

        return area;
    }

    /**
     * 42. Trapping Rain Water
     * dp
     * 基于暴力求解的改进
     * 引入动态规划思想，先计算并保存每个位置的max_left和max_right
     * time:O(n)    space:o(n)
     * pass  98%  92%
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if (height.length == 0)    return 0;

        int area = 0;
        int[] mls = new int[height.length];
        int[] mrs = new int[height.length];

        mls[0] = height[0];
        for (int i = 1; i < height.length; i++)    mls[i] = Math.max(mls[i-1], height[i]);

        mrs[height.length-1] =  height[height.length-1];
        for (int i=height.length-2; i>=0; i--)     mrs[i] = Math.max(mrs[i+1], height[i]);

        for (int i=1; i<height.length-1; i++)     area += Math.min(mls[i], mrs[i]) - height[i];

        return area;
    }

    /**
     * 42. Trapping Rain Water
     * two pointers
     * time:O(n)    space:O(1)
     * @param height
     * @return
     */
    public int trap3(int[] height){
        int area = 0;
        int left = 0, right = height.length - 1;
        int left_max = 0, right_max = 0;

        while (left < right){
            if (height[left] < height[right]){
                if (height[left] >= left_max)      left_max = height[left];
                else   area += left_max - height[left];
                left++;
            }else {
                if (height[right] >= right_max)    right_max = height[right];
                else   area += right_max - height[right];
                right--;
            }
        }

        return area;
    }

    /**
     * 50. Pow(x, n)
     * Medium
     * Implement pow(x, n), which calculates x raised to the power n (xn).
     * Example 1:
     * Input: 2.00000, 10
     * Output: 1024.00000
     * Example 2:
     * Input: 2.10000, 3
     * Output: 9.26100
     * Example 3:
     * Input: 2.00000, -2
     * Output: 0.25000
     * Explanation: 2-2 = 1/22 = 1/4 = 0.25
     * pass  100%  6%
     * 分治法，递归实现
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0)    return 1;
        if (n == 1)    return x;
        if (n == -1)    return 1 / x;

        int m = n / 2;
        double t = myPow(x, m);

        return t * t * (n%2==0 ? 1 : x);
    }

    /**
     * 50. Pow(x, n)
     * 迭代
     * pass  100%  6%
     * time:O(logn)
     * @param x
     * @param n
     * @return
     */
    public double myPow1(double x, int n) {
        long N = n;     //防止n为最小负数时取反越界

        if (N < 0){
            x = 1 / x;
            N = -N;
        }

        double pow = 1;
        double y = x;

        for (long i=N; i>0; i/=2){
            if (i%2 == 1)   pow *= y;
            y *= y;
        }

        return pow;
    }

    /**
     * 55. Jump Game
     * Medium
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * Example 1:
     * Input: [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     * Input: [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum
     *              jump length is 0, which makes it impossible to reach the last index.
     * pass  40%  100%
     * 贪心法求解
     * 设当前位置为i，跳到 下一步可以到达最远 的位置（期望位置），即j+nums[j]最大的位置；i+1 <= j <= i+nums[i]
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int i = 0;

        while (i < nums.length){
            if (i + nums[i] >= nums.length-1)   return true;

            int j = i;
            for (int k=i+1; k<=i+nums[i] && k<nums.length; k++)    j = k+nums[k] > j+nums[j] ? k : j;

            if (j == i)    break;

            i = j;
        }

        return false;
    }

    /**
     * 55. Jump Game
     * 贪心法改进版
     * 逆向思维：假设当前位置在末尾，往回走；若能走到起始位置，则成功
     * 往回走的过程中，维持当前可到达的离起始位置最近的位置
     * pass  99%  100%
     * @param nums
     * @return
     */
    public boolean canJump1(int[] nums) {
        int pos = nums.length - 1;
        for (int i=nums.length-1; i>=0; i--)    if (i+nums[i] >= pos)   pos = i;
        return pos == 0;
    }

    /**
     * 55. Jump Game
     * 回溯法 todo
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        return false;
    }

    /**
     * 55. Jump Game
     * 动态规划top-down todo
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums) {
        return false;
    }

    /**
     * 55. Jump Game
     * 动态规划bottom-up todo
     * @param nums
     * @return
     */
    public boolean canJump4(int[] nums) {
        return false;
    }

    /**
     * 56. Merge Intervals
     * Medium
     * Given a collection of intervals, merge all overlapping intervals.
     * Example 1:
     * Input: [[1,3],[2,6],[8,10],[15,18]]
     * Output: [[1,6],[8,10],[15,18]]
     * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
     * Example 2:
     * Input: [[1,4],[4,5]]
     * Output: [[1,5]]
     * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
     * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        return new int[1][];
    }

    /**
     * 66. Plus One
     * Easy
     * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
     * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
     * You may assume the integer does not contain any leading zero, except the number 0 itself.
     * Example 1:
     * Input: [1,2,3]
     * Output: [1,2,4]
     * Explanation: The array represents the integer 123.
     * Example 2:
     * Input: [4,3,2,1]
     * Output: [4,3,2,2]
     * Explanation: The array represents the integer 4321.
     * pass   100%  98%
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int carry = 1;
        int sum;
        for (int i = digits.length - 1; i >= 0; i--) {
            sum = digits[i] + carry;
            carry = sum / 10;
            digits[i] = sum % 10;
        }

        if (carry == 0)    return digits;

        int[] ret = new int[digits.length+1];
        ret[0] = carry;
        for (int i = 0; i < digits.length; i++)    ret[i+1] = digits[i];

        return ret;
    }

    /**
     * pass  100% 5%
     * 二分法
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x < 2)  return x;

        long num;
        int m, left = 2, right = x/2;

        while (left <= right){
            m = left + (right-left) / 2;
            num = (long)m * m;
            if (num > x)    right = m - 1;
            else if (num < x)   left = m + 1;
            else return m;
        }

        return right;
    }

    /**
     * 75. Sort Colors
     * Medium
     * Share
     * Given an array with n objects colored red, white or blue,
     * sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     * Note: You are not suppose to use the library's sort function for this problem.
     * Example:
     * Input: [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     * Follow up:
     * A rather straight forward solution is a two-pass algorithm using counting sort.
     * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
     * Could you come up with a one-pass algorithm using only constant space?
     * pass  100%  99%
     * 荷兰国旗问题
     * 3-ways-partition
     * @param nums
     */
    public void sortColors(int[] nums) {
        int i = 0, j = nums.length, k = 0;
        int pivot = 1;
        while(k <= j){
            if(nums[k] < pivot)    swap(nums, k++, i++);
            else if(nums[k] > pivot)    swap(nums, k, j--);
            else    k++;
        }
    }

    private void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


}
