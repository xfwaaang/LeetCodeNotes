package com.xf.algorithm.strmatharr;

import com.xf.test.TypeString;
import com.xf.test.TypeStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author xfwaaang
 * @create 2019-09-15 18:27
 *
 * 1. 实现2048左移操作
 * 2. 提取str中以空格隔开的数字（数字可能是多位数）
 * 3. 字符串去重
 * 4. 求数对之差的最大值
 * 5. 表达式解析
 * 6. 空格字符串分割
 * 7. 求无序数组的中位数
 */
public class Solution {

    /**
     * 实现2048左移操作
     * 输入：{"0 0 2 4", "0 2 2 2", "0 4 2 2", "8 8 2 2"}
     * 输出：多行字符串
     * 2 4 0 0
     * 4 2 0 0
     * 4 4 0 0
     * 16 4 0 0
     * 首先提取每行输入字符串的数字
     * 然后合并移位
     * @param input
     * @return
     */
    @TypeStrings({"0 0 2 4", "0 2 2 2", "0 4 2 2", "8 8 2 2"})
    public String leftMove2048(String[] input){
        StringBuilder sb = new StringBuilder();
        int j, m; //m为非零个数
        for(String str : input){
            List<Integer> list = getNums(str);
            //合并相同数字
            for(int i=0; i<list.size(); i++){
                if(list.get(i) != 0){
                    j = i + 1;
                    while (j < list.size() && list.get(i) != list.get(j))   j++;
                    if(j >= list.size())    continue;
                    list.set(i, list.get(i)*2);
                    list.set(j, 0);
                }
            }

            m = 0;
            //非零数字移到前面
            for (Integer num : list) {
                if(num != 0)
                {
                    sb.append(num).append(" ");
                    m++;
                }
            }
            //后面补齐0
            for(int i=0; i<list.size()-m; i++){
                sb.append(0).append(" ");
            }
            sb.replace(sb.length()-1, sb.length(), "\n");
        }

        return sb.toString();
    }

    /**
     * 提取str中的数字
     * @param str   数字以空格隔开
     * @return
     */
    private List<Integer> getNums(String str){
        List<Integer> list = new ArrayList<>();
        int num = 0;
        for(int i=0; i<=str.length(); i++){
            if(i == str.length() || str.charAt(i) == ' '){
                list.add(num);
                num = 0;
            }else {
                num = num * 10 + str.charAt(i) - '0';
            }
        }
        return list;
    }

    /**
     * 输入全为小写字母的字符串
     * 输出无重复字母的字符串
     * @param str
     * @return
     */
    public String unique(String str){
        StringBuilder sb = new StringBuilder();
        int[] letters = new int[26];
        for (int i = 0; i < letters.length; i++)    letters[i] = 0;
        for (char ch : str.toCharArray()) {
            if(letters[ch-'a'] == 0){
                sb.append(ch);
                letters[ch-'a'] = 1;
            }
        }
        return sb.toString();
    }

    /**
     * 求数对之差的最大值
     * 数组中的一个数减去它右边子数组中的一个数可以得到一个差值，求所有可能差值的最大值
     * 如[1,4,17,3,2,9]，最大差值为17-2=15
     * 动态规划
     * @param a
     * @return
     */
    public int maxNumPairDiff(int[] a){
        int maxE = a[0];
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 1; i < a.length; i++) {
            maxDiff = Math.max(maxDiff, maxE - a[i]);
            maxE = Math.max(maxE, a[i]);
        }
        return maxDiff;
    }

    /**
     * 表达式解析
     * 括号表示将里面的字符串反转
     * 输入：一行字符串
     * 输出：一行字符串、若括号不匹配，输出空字符串
     * input：((ur)oi)
     * output：iour
     * @param str
     * @return
     */
    @TypeString("((ur)oi)")
    public static String parseEX(String str){
        char[] chs = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        List<Character> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for (char ch : chs) {
            if(ch == ')'){
                list.clear();
                char c = ' ';
                while (!stack.empty()){
                    c = stack.pop();
                    if(c == '(')    break;
                    list.add(c);
                }
                if(c != '('){
                    return "";
                }
                if(stack.empty()){
                    for (Character character : list) {
                        sb.append(character);
                    }
                }else{
                    for (Character character : list) {
                        stack.push(character);
                    }
                }
            }else if(ch == '('){
                stack.push(ch);
            }else{
                if(!stack.empty()){
                    stack.push(ch);
                }else {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }

    /**
     * 空格字符串分割
     * @param str
     * @return
     */
    public String[] strSplit(String str){
        List<String> list = new ArrayList<>();
        int start = 0;

        for(int i=0; i<=str.length(); i++){
            if (i == str.length() || str.charAt(i) == ' '){
                list.add(str.substring(start, i));
                start = i + 1;
            }
        }

        String[] res = new String[list.size()];

        return list.toArray(res);
    }

    /**
     * 求无序数组的中位数，利用快速排序划分
     * 数组长度为偶数，则中位数为中间两个数字的平均值
     * 输入：{4,5,1,2,2,6}
     * 输出：3
     * @param nums
     * @return
     */
    public int mediumNum(int[] nums){
        int pos = - 1;
        int low = 0, high = nums.length - 1;
        int mid = (low + high) / 2;

        while(pos != mid){
            pos = partition(nums, low, high);
            if(pos > mid){
                high = pos - 1;
            }else if(pos < mid){
                low = pos + 1;
            }else{
                break;
            }
        }

        //处理偶数长度的数组
        if(nums.length % 2 == 0)
        {
            int mine = nums[mid+1];
            for(int i=mid+2; i<nums.length; ++i)	if(mine > nums[i])	mine = nums[i];
            return (nums[mid] + mine) / 2;
        }

        return nums[mid];
    }

    /**
     * 快速排序划分函数
     * @param nums
     * @param low
     * @param high
     * @return  基准元素最终的位置
     */
    private int partition(int[] nums, int low, int high){
        int pivot = nums[low];
        while(low < high){
            while(low < high && nums[high] >= pivot)    high--;
            nums[low] = nums[high];
            while(low < high && nums[low] <= pivot)     low++;
            nums[high] = nums[low];
        }
        nums[high] = pivot;
        return low;
    }
}
