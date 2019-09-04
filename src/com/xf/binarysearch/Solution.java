package com.xf.binarysearch;

/**
 * @author xfwaaang
 * @create 2019-06-18 11:24
 */
public class Solution {

    /**
     * 167. Two Sum II - Input array is sorted
     * Easy
     * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
     * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
     * Note:
     * Your returned answers (both index1 and index2) are not zero-based.
     * You may assume that each input would have exactly one solution and you may not use the same element twice.
     * Example:
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
     * pass  28%  34%
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        for (int i = 0; i < numbers.length-1; i++) {
            int m = searchHelper2(numbers, target-numbers[i], i+1, numbers.length-1);
            if (m != -1){
                res[0] = i + 1;
                res[1] = m + 1;
                break;
            }
        }

        return res;
    }

    /**
     * 167. Two Sum II - Input array is sorted
     * pass 100%  71%
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target){
        int i = 0, j = numbers.length - 1;
        int[] res = new int[2];

        while (i < j){
            int sum = numbers[i] + numbers[j];
            if (sum == target){
                res[0] = i + 1;
                res[1]= j + 1;
                break;
            }else if (sum > target){
                j--;
            }else {
                i++;
            }
        }

        return res;
    }

    /**
     * 350. Intersection of Two Arrays II
     * Easy
     * Given two arrays, write a function to compute their intersection.
     * Example 1:
     * Input: nums1 = [1,2,2,1], nums2 = [2,2]
     * Output: [2,2]
     * Example 2:
     * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * Output: [4,9]
     * Note:
     * Each element in the result should appear as many times as it shows in both arrays.
     * The result can be in any order.
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        return null;
    }

    /**
     * 704. Binary Search
     * Easy
     * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to search target in nums. If target exists, then return its index, otherwise return -1.
     * Example 1:
     * Input: nums = [-1,0,3,5,9,12], target = 9
     * Output: 4
     * Explanation: 9 exists in nums and its index is 4
     * Example 2:
     * Input: nums = [-1,0,3,5,9,12], target = 2
     * Output: -1
     * Explanation: 2 does not exist in nums so return -1
     * pass  100%  88%
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        return searchHelper2(nums, target, 0, nums.length-1);
    }

    private int searchHelper2(int[] nums, int target, int s, int e) {
        while(s <= e){
            int m = (s + e) / 2;
            if (nums[m] == target){
                return m;
            } else if (nums[m] > target){
                e = m - 1;
            }else {
                s = m + 1;
            }
        }

        return -1;
    }

    private int searchHelper(int[] nums, int target, int s, int e) {
        if (s > e){
            return -1;
        }

        int m = (s + e) / 2;

        if (nums[m] == target){
            return m;
        }else if (nums[m] > target){
            return searchHelper(nums, target, s, m-1);
        }else {
            return searchHelper(nums, target, m+1, e);
        }

    }

}
