package com.xf.algorithm.dac;

/**
 * @author xfwaaang
 * @create 2019-05-05 9:09
 * divide and conquer
 */
public class Solution {
    /**
     * 4. Median of Two Sorted Arrays
     * todo
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
         return findMedianSortedArraysHelper(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1);
    }

    private double findMedianSortedArraysHelper(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2) {

        return 0;
    }

    /**
     * 23. Merge k Sorted Lists
     * pass  99%  56%
     * 分治法
     * 最终转化为合并两个有序链表，借鉴归并排序的思想，即：21. Merge Two Sorted Lists
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)  return null;
        return mergeKListsHelper(lists, 0, lists.length-1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int s, int e) {
        if (s > e)  return null;
        if (s == e) return lists[s];

        int m = (s + e) / 2;

        ListNode l1 = mergeKListsHelper(lists, s, m);
        ListNode l2 = mergeKListsHelper(lists, m+1, e);

        return mergeKListsMerge(l1, l2);
    }

    /**
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mergeKListsMerge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        head.next = null;
        ListNode p = head;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null){
            ListNode tmp = new ListNode(0);
            tmp.next = null;
            if (p1.val < p2.val){
                tmp.val = p1.val;
                p1 = p1.next;
            }else {
                tmp.val = p2.val;
                p2 = p2.next;
            }
            p.next = tmp;
            p = p.next;
        }

        if (p1 != null)  p.next = p1;
        else if (p2 != null)  p.next = p2;

        return head.next;
    }

    /**
     * 215. Kth Largest Element in an Array
     * pass  30%  59%
     * 借鉴快速排序的思想
     * 1. 对数组进行划分，使得基准元素小于其左边元素，大于其右边元素，划分结束得到基准元素最终的位置 pivot 及 在当前子数组的 相对位置 pos（表示当前子数组的第 pos 大的元素）
     * 2. 若 pos = k，则该元素为所求元素
     * 3. 若 pos > k，所求元素在数组的 左边，即求 左边子数组的第 k 大元素
     * 4. 若 pos < k，所求元素在数组的 右边，即求 右边子数组的第 k-pos 大元素
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return findKthLargestHelper(nums, 0, nums.length-1, k);
    }

    private int findKthLargestHelper(int[] nums, int s, int e, int k) {
        if (s > e)  return 0;

        int pivot = partition(nums, s, e);
        int pos = pivot - s + 1;
        if (pos == k){
            return nums[pivot];
        }else if (pos > k){
            return findKthLargestHelper(nums, s, pivot-1, k);
        }else {
            return findKthLargestHelper(nums, pivot+1, e, k-pos);
        }
    }

    private int partition(int[] nums, int s, int e) {
        int pivot = nums[s];

        while(s < e)
        {
            while(s < e && nums[e] <= pivot)
                --e;
            nums[s] = nums[e];

            while(s < e && nums[s] >= pivot)
                ++s;
            nums[e] = nums[s];
        }

        nums[e] = pivot;

        return s;
    }

    /**
     * 240. Search a 2D Matrix II
     * pass  23%  99%
     * 二维二分查找
     * 对于矩阵中的某个元素，位于其左边和上边的元素都有可能比它小，位于其右边和下边的元素都有可能比它大
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        return searchMatrixHelper(matrix, 0, 0, matrix.length-1, matrix[0].length-1, target);
    }

    private boolean searchMatrixHelper(int[][] matrix, int x1, int y1, int x2, int y2, int target) {
        if (x1 > x2 || y1 > y2) return false;

        if (x1 == x2 && y1 == y2){
            if (target == matrix[x1][y1])   return true;
            else return false;
        }

        int i = (x1 + x2) >> 1;
        int j = (y1 + y2) >> 1;

        if (matrix[i][j] > target){
            return searchMatrixHelper(matrix, x1, y1, x2, j-1, target) || searchMatrixHelper(matrix, x1, j, i-1, y2, target);
        }else if (matrix[i][j] < target){
            return searchMatrixHelper(matrix, x1, j+1, i, y2, target) || searchMatrixHelper(matrix, i+1, y1, x2, y2, target);
        }

        return true;
    }

    /**
     * 312. Burst Balloons
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        return 0;
    }

    /**
     * 327. Count of Range Sum
     * pass  25%  81%
     * 注意整数求和与求积会越界
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        return countRangeSumHelper(nums, 0, nums.length-1, lower, upper);
    }

    private int countRangeSumHelper(int[] nums, int s, int e, int lower, int upper) {
        if (s > e)  return 0;
        if (s == e) return (nums[s] >= lower && nums[s] <= upper) ? 1 : 0;
        int m = (s + e) >> 1;
        return countRangeSumHelper(nums, s, m, lower, upper) + countRangeSumHelper(nums, m+1, e, lower, upper) + countRangeSumMerge(nums, s, m, e, lower, upper);
    }

    private int countRangeSumMerge(int[] nums, int s, int m, int e, int lower, int upper) {
        int count = 0;

        long leftSum = 0, rightSum ;
        for (int i=m; i>=s; i--){
            leftSum += nums[i];
            rightSum = 0;
            for (int j=m+1; j<=e; j++){
                rightSum += nums[j];
                long sum = leftSum + rightSum;
                if (sum >= lower && sum <= upper)   count++;
            }
        }

        return count;
    }

    /**
     * 493. Reverse Pairs
     * 对于数组nums，如果i<j和 nums[i]>2*nums[j] ，我们称（i，j）为一个重要的反向对。
     * 您需要返回给定数组中重要的反转对的数目。
     * Time Limit Exceeded  todo
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        int res = reversePairsHelper(nums, 0, nums.length-1);
        for (int num : nums) {
            System.out.print(num + ",");
        }
        System.out.println();
        return res;
    }


    private int reversePairsHelper(int[] nums, int low, int high) {
        if (low >= high)   return 0;
        int mid = (low + high) >> 1;
        int left = reversePairsHelper(nums, low, mid);
        int right = reversePairsHelper(nums, mid+1, high);
        int merge = reversePairsMerge(nums, low, mid, high);
        return left + right + merge;
    }

    private int reversePairsMerge(int[] nums, int low, int mid, int high) {
        int[] tmp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = nums[i];
        }

        int i = low, j = mid + 1;
        int count = 0;
        int k = low;

        while (i <= mid && j <= high){
            if (tmp[i] >= tmp[j]){
                int t = i;
//                找到i开始的第一个逆序对
                while (t <= mid && tmp[t] <= 2.0*tmp[j])  t++;
                if (t <= mid)   count += mid - t + 1;

                nums[k++] = tmp[j++];
            }else {
                nums[k++] = tmp[i++];
            }
        }

        while (i <= mid)    nums[k++] = tmp[i++];
        while (j <= high)   nums[k++] = tmp[j++];

        return count;
    }


    /**
     * 932. Beautiful Array
     * 对于某些固定n，如果数组a是整数1、2、…、n的排列，则它是美丽的，这样：
     * 对于每个i<j，没有k与i<k<j，这样a[k]*2=a[i]+a[j]。
     * 给定n，返回任何漂亮的数组a（保证存在一个数组）。
     * todo
     * @param N
     * @return
     */
    public int[] beautifulArray(int N) {
        return null;
    }



}
