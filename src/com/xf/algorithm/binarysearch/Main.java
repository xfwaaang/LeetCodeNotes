package com.xf.algorithm.binarysearch;

/**
 * @author xfwaaang
 * @create 2019-06-18 16:07
 */
public class Main {

    public static void main(String[] args){
        Solution solution = new Solution();

        int[] nums = {-1,0,3,5,9,12};
        int target = 3;
        System.out.println(solution.search(nums, target));
    }
}
