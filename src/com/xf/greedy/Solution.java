package com.xf.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author xfwaaang
 * @create 2019-03-26 10:19
 */
public class Solution {

    /**
     * 455. Assign Cookies
     * pass
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        int res = 0;
        int i = 0, j = 0;
        int m = g.length, n = s.length;

        Arrays.sort(g);
        Arrays.sort(s);

        while (i < m && j < n){
            while (j < n && g[i] > s[j])    j++;
            if (j == n){
                break;
            }
            res++;
            i++;
            j++;
        }

        return res;
    }

    /**
     * 860. Lemonade Change
     * pass
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;

        for (int bill : bills) {
            if (bill == 5){
                five++;
            }else if (bill == 10){
                if (five == 0){
                    return false;
                }
                five--;
                ten++;
            }else if (bill == 20){
                if (ten == 0){
                    if (five < 3){
                        return false;
                    }
                    five -= 3;
                }else {
                    if (five == 0){
                        return false;
                    }
                    five--;
                    ten--;
                }
            }
        }

        return true;
    }

    /**
     * 861. Score After Flipping Matrix
     * pass     100%    6%
     * 越靠前的列 1 的数目越多，则最终的和越大
     * 1. 对于第一列，若某个值为 0 ，则将当前行toggle
     * 2. 从第二列至最后一列，若当前列 0 的数目大于行数的一半，则toggle当前列
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        int n = A.length;
        int m = A[0].length;

//        处理第一列
        for (int i=0; i<n; i++){
            if (A[i][0] == 0){
                for (int j=0; j<m; j++)   A[i][j] ^= 1;
            }
        }

//        处理第二至m列
        for (int i=1; i<m; i++){
            int zero = 0;
            for (int j=0; j<n; j++){
                if (A[j][i] == 0)   zero++;
            }
            if (zero > n / 2){
                for (int h=0; h<n; h++)     A[h][i] ^= 1;
            }
        }

        int res = 0;
        int k = 1;
//        求A的和
        for (int i=m-1; i>=0; i--){
            for(int j=0; j<n; j++){
                res += k * A[j][i];
            }
            k *= 2;
        }

        return res;
    }

    /**
     * 763. Partition Labels
     * pass  96%    85%
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();

//        start：子串起始位置，end：子串结束位置
        int start = 0, end = 0;

        while (end < S.length()){
//            求出当前子串每个字符在S中出现的最靠后的位置 j
//            若j > end，则end = j
            for (int i=start; i<=end; i++){
//                从后往前找
                int j = S.length() - 1;
                while (j > end && S.charAt(i) != S.charAt(j))      j--;
                end = j;
            }

            res.add(end - start + 1);

//            更新start和end
            end += 1;
            start = end;

        }

        return res;
    }

    /**
     * 921. Minimum Add to Make Parentheses Valid
     * @param S
     * @return
     */
    public int minAddToMakeValid(String S) {
        Stack<Integer> help = new Stack<>();

        for (int i=0, n=S.length(); i<n; i++){
            if (S.charAt(i) == '('){
                help.push(0);
            }else{
                if (help.empty()){
                    help.push(1);
                }else {
                    if (help.peek() == 0){
                        help.pop();
                    }else {
                        help.push(1);
                    }
                }
            }
        }

        return help.size();
    }

    /**
     * 1005. Maximize Sum Of Array After K Negations
     * @param A
     * @param K
     * @return
     */
    public int largestSumAfterKNegations(int[] A, int K) {
        int minPos = 0;
        int maxSum = 0;

        while (K-- > 0){
            for (int i = 0; i < A.length; i++) {
                if (A[i] < A[minPos]){
                    minPos = i;
                }
            }
            A[minPos] = -A[minPos];
        }

        for (int i : A)    maxSum += i;

        return maxSum;
    }


}
