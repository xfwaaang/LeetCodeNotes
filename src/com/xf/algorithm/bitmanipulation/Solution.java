package com.xf.algorithm.bitmanipulation;

/**
 * @author xfwaaang
 * @create 2019-09-04 16:58
 */
public class Solution {
    /**
     * 137. Single Number II
     * Medium
     * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
     * Note:
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     * Example 1:
     * Input: [2,2,3,2]
     * Output: 3
     * Example 2:
     * Input: [0,1,0,1,0,1,99]
     * Output: 99
     * pass  60%  100%
     * 一个数组中只有一个数字出现次数为一，其余数字均出现三次，求出那一个数字
     * 将每个数字表示为32位二进制，如果n-1个数字出现3次，则对应位的二进制1的个数可以被3整除
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int ret = 0;
        int[] bits = new int[32];
        int t;

        for (int num : nums) {
            t = num;
            for (int j = 0; j < 32; j++) {
                bits[j] += (t & 1);
                t >>= 1;
            }

        }

        t = 1;
        for (int bit : bits){
            if (bit % 3 != 0)   ret += t;
            t <<= 1;
        }

        return ret;
    }


}
