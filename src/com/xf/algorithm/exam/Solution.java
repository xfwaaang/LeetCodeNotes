package com.xf.algorithm.exam;

/**
 * @author xfwaaang
 * @create 2019-09-04 21:38
 */
public class Solution {

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
     * 相邻两个数字的差值为移动距离，移动数组中的数字使得数组的总移动距离最小，返回最小移动的次数
     * 输入：{4,2,7,6}  总移动距离为2+5+1=8
     * 输出：2
     * 表示最少移动2次，数组的总移动距离最小；{2,4,6,7}，总移动距离为2+2+1=5
     * 即求逆序对个数
     * 见 com.xf.algorithm.dac.Solution#reversePairs0(int[])
     * @param nums
     * @return
     */
    public int minMoveTimes(int[] nums){
        int res = 0;
        return res;
    }


}
