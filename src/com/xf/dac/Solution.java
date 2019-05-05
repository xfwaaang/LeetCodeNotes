package com.xf.dac;

/**
 * @author xfwaaang
 * @create 2019-05-05 9:09
 * divide and conquer
 */
public class Solution {

    /**
     * 932. Beautiful Array
     * @param N
     * @return
     */
    public int[] beautifulArray(int N) {
        return beautifulArrayHelper(1, N);
    }

    private int[] beautifulArrayHelper(int i, int j) {
        if (j - i < 0)  return new int[0];
        if (j - i < 2){
            int[] tmp = new int[j-i+1];
            int t = i;
            for (int k = 0; k < tmp.length; k++) {
                tmp[k] = t;
                t += 1;
            }
            return tmp;
        }

        int m = (i + j) / 2;
        int[] a = beautifulArrayHelper(i, m);
        int[] b = beautifulArrayHelper(m+1, j);

        return beautifulArrayMerge(a, b);
    }

    private int[] beautifulArrayMerge(int[] a, int[] b) {

        return new int[0];
    }

}
