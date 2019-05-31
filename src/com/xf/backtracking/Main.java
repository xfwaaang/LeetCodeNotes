package com.xf.backtracking;

/**
 * @author xfwaaang
 * @create 2019-05-29 10:38
 */
public class Main {
    public static void main(String[] args){
        Solution solution = new Solution();

        int n = 2;
        System.out.println(solution.generateParenthesis(n));

        int[] nums = {1, 2, 3, 4};
        int m = 3;
        System.out.println(PermuteAndCombine.combine(nums, m));
        System.out.println(PermuteAndCombine.combine2(nums, m));
        System.out.println(PermuteAndCombine.combine3(nums, m));


    }

}
