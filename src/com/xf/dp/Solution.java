package com.xf.dp;

/**
 * @author xfwaaang
 * @create 2019-04-16 11:52
 * 动态规划
 */
public class Solution {

    /**
     * 53. Maximum Subarray
     * pass  97%  87%
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum < 0)    sum = 0;
            sum += nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    /**
     * 121. Best Time to Buy and Sell Stock
     * not dp  pass  80%  60%
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0)     return 0;

        int max_profit = 0;
        int min_price = prices[0];
        for (int i=1; i<prices.length; i++){
            max_profit = Math.max(max_profit, prices[i] - min_price);
            min_price = Math.min(min_price, prices[i]);
        }

        return max_profit;
    }

    /**
     * 121. Best Time to Buy and Sell Stock
     *  dp  pass  80%   60%
     * @param prices
     * @return
     */
    public int maxProfit_dp(int[] prices) {
        if (prices.length == 0)     return 0;

        int[] dp = new int[prices.length+1];
        dp[0] = 0;
        dp[1] = 0;
        int min_price = prices[0];
        for (int i=2; i<prices.length+1; i++){
            dp[i] = Math.max(dp[i-1], prices[i-1] - min_price);
            min_price = Math.min(min_price, prices[i-1]);
        }

        return dp[prices.length];
    }

    /**
     * 198. House Robber
     * pass   100%   92%
     * 设房屋 nums[1:n], dp[i] 代表 nums[1:i]的最优结果
     * 考虑第 i 个房屋 nums[i]，有两种情况
     *  1. rob ，此时不能 rob nums[i-1]，但可以 rob nums[i-2]，则 dp[i] = dp[i-2] + nums[i]
     *  2. not rob，此时可以rob nums[i-1]，则 dp[i] = dp[i-1]
     *  dp[i]为两者最大值
     *  注意：数组 nums 下标从 0 开始
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0)   return 0;

        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i=2; i<=nums.length; i++){
            dp[i] = Math.max(dp[i-2] + nums[i-1], dp[i-1]);
        }

        return dp[nums.length];
    }

    /**
     * 338. Counting Bits
     * pass  99%  100%
     * dp[i] 表示 i 的二进制表示中 1 的个数
     * 考虑 i 的奇偶性
     * 1. i 为偶数
     *   i = i/2 + i/2  -->  dp[i] = dp[i/2]
     *   两个相同数字相加不改变其二进制表示中 1 的个数
     * 2. i 为奇数
     *   i = i-1 + 1  -->  dp[i] = dp[i-1] + 1
     *   i 为奇数，则 i-1 为偶数，其二进制表示的最低位为 0，加上 1，不产生进位
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] dp = new int[num+1];
        dp[0] = 0;
        for (int i=1; i<=num; i++){
            if (i % 2 == 0){
                dp[i] = dp[i/2];
            }else {
                dp[i] = dp[i-1] + 1;
            }
        }
        return dp;
    }




    /**
     * 746. Min Cost Climbing Stairs
     * pass   100%   80%
     * 一次可以爬一个或两个台阶，从某个台阶离开需要cost
     * 考虑第 i （从1开始）个台阶，爬到第 i 个台阶的top有两种情况（第 i 台阶为第 i-1 台阶的top）
     * 1. 踩台阶 i，从台阶 i 直接跳到 top，   dp(i-1) + cost(i)
     * 2. 没踩台阶 i，从台阶 i-1 跳过 i，到达top，   dp(i-2) + cost(i-1)
     * 取两者最小值
     * 注意数组 cost 下标从 0 开始
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length+1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = Math.min(cost[0], cost[1]);
        for (int i=3; i<cost.length+1; i++){
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[cost.length];
    }

    /**
     * 877. Stone Game
     * pass   26%   35%
     * 设 dp[i][j] 表示 piles[i-1] - piles[j-1]，Alex and Lee之间stone数的差值，dp[i][j] > 0，表示 Alex wins
     * Alex 先取一个石碓，有两种情况，Lee 再取一个石碓，又有两种情况，总共有四种情况
     * 1. Alex 取 piles[j-1]
     *    a. Lee 取 piles[j-2]  -->  piles[j-1] - piles[j-2] + dp[i][j-2]
     *    b. Lee 取 piles[i-1]  -->  piles[j-1] - piles[i-1] + dp[i+1][j-1]
     * 2. Alex 取 piles[i-1]
     *    a. Lee 取 piles[j-1]  -->  piles[i-1] - piles[j-1] + dp[i+1][j-1]
     *    b. Lee 取 piles[i]    -->  piles[i-1] - piles[i] + dp[i+2][j]
     * 取四者最大值，即为 dp[i][j]
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int[][] dp = new int[piles.length+1][piles.length+1];

        for (int i = 1; i < piles.length; i++) {
            dp[i][i+1] = Math.abs(piles[i-1] - piles[i]);
        }

        for (int k=3; k<piles.length; k+=2){
            for (int i=1; i<=piles.length-k; i++){
               int j = i + k;
               dp[i][j] = Math.max(
                   Math.max(piles[j-1] - piles[j-2] + dp[i][j-2], piles[j-1] - piles[i-1] + dp[i+1][j-1])
                   , Math.max(piles[i-1] - piles[j-1] + dp[i+1][j-1], piles[i-1] - piles[i] + dp[i+2][j])
               );
            }
        }

        return dp[1][piles.length] > 0;
    }

    /**
     * 877. Stone Game
     * 优化 todo
     * @param piles
     * @return
     */
    public boolean stoneGame_pro(int[] piles) {

        return false;
    }

    public static void main(String[] args){
//        [1,2,3,4,6,8,9,10,13,14,16,17,19,21,24,26,27,28,29] [3,14,50]
        int[] days = {1,2,3,4,6,8,9,10,13,14,16,17,19,21,24,26,27,28,29};
        int[] costs = {3,14,50};
        System.out.println(mincostTickets(days, costs));
    }

    /**
     * 983. Minimum Cost For Tickets
     * todo
     * @param days
     * @param costs
     * @return
     */
    public static int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[days.length+1];

        return dp[days.length];
    }



    /**
     * 1025. Divisor Game
     * pass   100%  100%
     * dp[n] = any{ dp[n-x] == false },   0 < x < n && n % x == 0
     * 若存在 dp[n-x] == false, 则 dp[n] == true；否则 dp[n] == false
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N+1];
        dp[0] = false;
        dp[1] = false;
        for (int i=2; i<N+1; i++){
            dp[i] = false;
            for (int x=1; x<i && i%x==0; x++){
                if (!dp[i-x]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }
}
