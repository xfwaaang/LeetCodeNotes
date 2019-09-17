package com.xf.algorithm.pointer;

import com.xf.test.TypeString;

/**
 * @author xfwaaang
 * @create 2019-09-14 12:05
 *
 * 指针问题
 * 1. 最小三元组距离
 * 2. 小招猫的机器人
 */
public class Solution {
    /**
     * 最小三元组距离
     * 从三个数组中各取一个数字使得三元组距离最小，返回最小距离
     * 三元组距离为三个数其中两个数字之差绝对值的最大值
     * 输入：{3,4,5,7}，{10,12,14,16,17}，{20,21,23,24,27,30}
     * 输出：13
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minTriadDis(int[] a, int[] b, int[] c){
        int minDis = Integer.MAX_VALUE, curDis;
        int i = 0, j = 0, k = 0;
        int minV;

        while (i < a.length && j < b.length && k < c.length){
            curDis = Math.max(Math.abs(a[i]-b[j]), Math.max(Math.abs(a[i]-c[k]), Math.abs(b[j]-c[k])));
            if(curDis < minDis)    minDis = curDis;

            minV = Math.min(a[i], Math.min(b[j], c[k]));

            if(minV == a[i])    i++;
            else if (minV == b[j])    j++;
            else   k++;
        }

        return minDis;
    }

    /**
     * 有n个点，初始每个点上都有一个机器人
     * 给定一个字符串，每个字符对应一个点，字符为L或R，L表示向左移动，R表示向右移动
     * 每个机器人需要移动10^100次，求最终每个点上的机器人数
     * 输入：RRLRL     一个字符串
     * 输出：0 1 2 1 1     表示每个点上机器人数目
     * 输入：RRRRRLRLRL
     * 输出：0 0 0 0 3 3 1 1 1 1
     * @param str
     * @return
     */
    @TypeString("RRRRRLRLRL")
    public int[] botCount(String str){
        int i = 0, n = str.length();
        int j;
        int res[] = new int[n];
        while(i < n){
            if(str.charAt(i) == 'R'){
                j = i + 1;
                while (j < n && str.charAt(j) == 'R')   j++;
                if((j-i) % 2 == 0){
                    res[j]++;
                }else {
                    res[j-1]++;
                }
            }else {
                j = i - 1;
                while(j >= 0 && str.charAt(j) == 'L')   j--;
                if((i-j) % 2 == 0){
                    res[j]++;
                }else {
                    res[j+1]++;
                }
            }
            i++;
        }
        return res;
    }

}
