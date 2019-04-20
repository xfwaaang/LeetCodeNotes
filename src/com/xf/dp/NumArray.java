package com.xf.dp;

/**
 * @author xfwaaang
 * @create 2019-04-19 18:01
 *
 * 303. Range Sum Query - Immutable
 * pass  77%   52%
 */
public class NumArray {
    private int[] sums;

    public NumArray(int[] nums) {
        sums = new int[nums.length+1];
        int sum = 0;
        sums[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sums[i+1] = sum;
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }

}
