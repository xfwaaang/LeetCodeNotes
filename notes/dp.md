### dp
#### 解题心得
1. 二维动态规划
    1. 左上到右下遍历 等价于 按行遍历
    2. 同样道理：左下到右上遍历 等价于 倒序按行遍历（从最后一行开始），不过此类问题多是从斜对角线开始的并且行数与列数相等，无论哪种方法都较为简洁
    3. 左下到右上，一般是从对角线开始遍历
2. 转换
    1. 比大小可以转化为差值。。。

#### 题目示例
##### 一维
###### [53. Maximum Subarray](https://leetcode.com/problems/maximum-subarray)
```java
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
```
###### [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
> 假设您有一个数组，其中第i个元素是第一天给定股票的价格。  
  如果你只被允许完成至多一笔交易（即买一份，卖一份股票），设计一个算法来找到最大利润。  
  请注意，在购买股票之前，您不能出售股票。
```java
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
```
```java
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
```
###### [198. House Robber](https://leetcode.com/problems/house-robber/)
> 你是一个计划沿着街道抢劫房屋的专业抢劫犯。每栋房子都有一定数量的钱被藏起来，唯一阻止你抢劫它们的限制是相邻的房子都有安全系统连接，如果两个相邻的房子在同一个晚上被闯入，它会自动联系警察。  
  给出一个非负整数的列表，代表每个房子的钱的数量，确定你今晚可以抢劫的最大金额，而不需要报警。

设房屋 `nums[1:n]`, `dp[i]` 代表 `nums[1:i]`的最优结果  
考虑第 `i` 个房屋 `nums[i]`，有两种情况  
1. `rob` ，此时不能 `rob` `nums[i-1]`，但可以 `rob` `nums[i-2]`，则 `dp[i] = dp[i-2] + nums[i]`
2. `not rob`，此时可以`rob` `nums[i-1]`，则 `dp[i] = dp[i-1]`

`dp[i]`为两者最大值  
注意：数组 `nums` 下标从 `0` 开始

```java
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
```

###### [338. Counting Bits](https://leetcode.com/problems/counting-bits/)

> 给定一个非负整数num。对于0≤i≤num范围内的每个数字i，计算其二进制表示形式中的1个数，并将其作为数组返回。

`dp[i]` 表示 `i` 的二进制表示中 `1` 的个数  
考虑 `i` 的奇偶性  
1. `i` 为偶数
`i = i/2 + i/2`  -->  `dp[i] = dp[i/2]`  
两个相同数字相加不改变其二进制表示中 1 的个数
2. `i` 为奇数
`i = i-1 + 1`  -->  `dp[i] = dp[i-1] + 1`  
`i` 为奇数，则 `i-1` 为偶数，其二进制表示的最低位为 `0`，加上 `1`，不产生进位

```java
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
```

###### [343. Integer Break](https://leetcode.com/problems/integer-break/)

> 给定一个正整数n，将其分解为至少两个正整数之和，并使这些整数的乘积最大化。返回您能得到的最大产品。

设 `dp[i]` 表示 `integerBreak(i)`  
`dp[i] = max{ max(k, dp[k]) * (i-k) }     1 <= k < i`

```java
public int integerBreak(int n) {
    int[] dp = new int[n+1];
    dp[1] = 0;
    dp[2] = 1;
    for (int i=3; i<=n; i++){
        dp[i] = -1;
        for (int k=1; k<i; k++){
            dp[i] = Math.max(dp[i], Math.max(k, dp[k]) * (i-k));
        }
    }
    return dp[n];
}
```

###### [413. Arithmetic Slices](https://leetcode.com/problems/arithmetic-slices/)

> 如果一个数列由至少三个元素组成，并且任意两个连续元素之间的差相同，则称为算术。  
  给出了一个由n个数组成的零索引数组a。该数组的一个切片是任何一对整数（p，q），因此0<=p<q<n。  
  如果序列：  
  A[P]，A[P+1]，…，A[Q-1]，A[Q]是算术。特别是，这意味着p+1<q。  
  函数应返回数组A中的算术片数。  
  Example:  
  A = [1, 2, 3, 4]  
  return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
  
`A[1:i] : A[0] - A[i-1]`  
`dp[i] : numberOfArithmeticSlices( A[1:i] )`  
`k` 表示 `A[1:i]` 中以 `A[i-1]` 结尾的个数（最小长度为 `2`）  
考虑 `A[i-1]`，有两种情况  
1. 子数组不包括 `A[i-1]`  
 此时 `numberOfArithmeticSlices` 为 `dp[i-1]`
2. 子数组包括 `A[i-1]`  
 此时 `numberOfArithmeticSlices` 为 `k` 或 `0`
 
`dp[i]` 为两者之和

```java
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3)   return 0;
        int[] dp = new int[A.length+1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 0;
        int k = 1;
        for (int i=3; i<=A.length; i++){
            dp[i] = dp[i-1];
            if (A[i-1] - A[i-2] == A[i-2] - A[i-3]){
                dp[i] += k;
                k += 1;
            }else {
                k = 1;
            }
        }
        return dp[A.length];
    }
```

###### [650. 2 Keys Keyboard](https://leetcode.com/problems/2-keys-keyboard/)

> 最初在记事本上，只有一个字符“A”存在。对于每个步骤，您可以在此记事本上执行两个操作：  
  全部复制：您可以复制记事本上的所有字符（不允许部分复制）。  
  粘贴：可以粘贴上次复制的字符。  
  给定一个数字n。您必须通过执行允许的最小步骤数，在记事本上精确地得到n“a”。  
  输出得到n'a'的最小步数。
  
`dp[i] = min{ dp[k] + i/k }   i % k == 0, 1 <= k <= i/2`

```java
    public int minSteps(int n) {
        int[] dp = new int[n+1];
        dp[1] = 0;
        for (int i=2; i<=n; i++){
            dp[i] = Integer.MAX_VALUE;
            for (int k=1; k<=i/2; k++){
                if (i % k == 0){
                    dp[i] = Math.min(dp[i], dp[k] + i/k);
                }
            }
        }
        return dp[n];
    }
```

###### [746. Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/)

> 在楼梯上，第i步分配了一些非负成本（索引为0）。  
  一旦你支付了费用，你就可以爬一两步。您需要找到到达顶层的最低成本，您可以从索引为0的步骤开始，也可以从索引为1的步骤开始。
  
一次可以爬一个或两个台阶，从某个台阶离开需要`cost`  
考虑第 `i` （从`1`开始）个台阶，爬到第 `i` 个台阶的`top`有两种情况（第 `i` 台阶为第 `i-1` 台阶的`top`）  
1. 踩台阶 `i`，从台阶 `i` 直接跳到 `top`，   `dp(i-1) + cost(i)`
2. 没踩台阶 `i`，从台阶 `i-1` 跳过 `i`，到达`top`，   `dp(i-2) + cost(i-1)`

取两者最小值  
注意数组 `cost` 下标从 `0` 开始 

```java
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
```

###### [1025. Divisor Game](https://leetcode.com/problems/divisor-game/)

> 爱丽丝和鲍勃轮流玩游戏，爱丽丝先开始。  
  最初，黑板上有一个数字n。在每个玩家的回合中，该玩家的移动包括：  
  选择0<x<n且n%x==0的任何x。  
  将黑板上的数字n替换为n-x。  
  另外，如果一个玩家不能移动，他们就会输掉比赛。  
  如果且仅当爱丽丝赢了比赛，则返回true，假设两个玩家都发挥最佳。
  
`dp[n] = any{ dp[n-x] == false },   0 < x < n && n % x == 0`  
若存在 `dp[n-x] == false`, 则 `dp[n] == true`；否则 `dp[n] == false`

```java
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
```

##### 二维

###### [62. Unique Paths](https://leetcode.com/problems/unique-paths)
> 机器人位于M x N网格的左上角（下图中标记为“开始”）。  
  机器人只能在任何时间点向下或向右移动。机器人正试图到达网格的右下角（在下图中标记为“完成”）。  
  有多少可能的唯一路径？

设 `dp[i][j]` 表示 `uniquePaths(j, i)`  
则，`dp[i][j] = dp[i-1][j] + dp[i][j-1]`

```java
    public int uniquePaths(int m, int n)  {
        int[][] dp = new int[n+1][m+1];
    
        dp[1][1] = 1;
        for (int i=1; i<=m; i++)    dp[0][i] = 0;
        for (int i=1; i<=n; i++)    dp[i][0] = 0;
    
    //        1.按行遍历
        for (int i=1; i<=n; i++){
            for (int j=1; j<=m; j++){
                if (i == 1 && j == 1)   continue;
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
    
    //        2.左上到右下遍历
    //        for (int k=2; k<=m; k++){
    //            int j = k;
    //            for (int i=1; i<=k && i<=n; i++){
    //                dp[i][j] = dp[i-1][j] + dp[i][j-1];
    //                j -= 1;
    //            }
    //        }
    //
    //        for (int k=2; k<=n; k++){
    //            int i = k;
    //            for (int j=m; j>0 && i<=n; j--){
    //                dp[i][j] = dp[i-1][j] + dp[i][j-1];
    //                i += 1;
    //            }
    //        }
    
        return dp[n][m];
    }
```

###### [64. Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/)
> 给定一个由非负数填充的m x n网格，找到一条从左上到右下的路径，该路径使沿其路径的所有数字之和最小化。  
  注意：您只能在任何时间点向下或向右移动。

设 `dp[i][j]` 表示 `minPathSum(grid)`  
则，`dp[i][j] = min{dp[i-1][j], dp[i][j-1]} + grid[i-1][j-1]`

```java
public int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m+1][n+1];

    dp[1][1] = grid[0][0];
    for (int i=1; i<=m; i++)    dp[i][0] = Integer.MAX_VALUE;
    for (int i=1; i<=n; i++)    dp[0][i] = Integer.MAX_VALUE;

//        按行遍历
    for (int i=1; i<=m; i++){
        for (int j=1; j<=n; j++){
            if (i == 1 && j == 1)   continue;
            dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
        }
    }

//        左上到右下遍历
//        for (int k=2; k<=n; k++){
//            int j = k;
//            for (int i=1; i<=k && i<=m; i++){
//                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
//                j -= 1;
//            }
//        }
//
//        for (int k=2; k<=m; k++){
//            int i = k;
//            for (int j=n; j>0 && i<=m; j--){
//                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
//                i += 1;
//            }
//        }

    return dp[m][n];
}
```

###### [877. Stone Game](https://leetcode.com/problems/stone-game/)

> 亚历克斯和李用一堆石头玩游戏。成排排列的桩数是偶数，每根桩的石桩数为正整数[i]。  
  游戏的目标是用最多的石头结束。石头的总数是奇数，所以没有联系。  
  亚历克斯和李轮流上场，亚历克斯先上场。每转一圈，玩家就从一排的开始或结束处取走整堆石头。这一直持续到没有更多的堆，在这一点上，石头最多的人获胜。  
  假设亚历克斯和李打得最好，如果并且只有亚历克斯赢了比赛才返回真。
  
设 `dp[i][j]` 表示 `piles[i-1] - piles[j-1]`，Alex and Lee之间`stone`数的差值，`dp[i][j] > 0`，表示 Alex wins  
Alex 先取一个石碓，有两种情况，Lee 再取一个石碓，又有两种情况，总共有四种情况  
1. Alex 取 `piles[j-1]`
    1. Lee 取 `piles[j-2]  -->  piles[j-1] - piles[j-2] + dp[i][j-2]`
    2. Lee 取 `piles[i-1]  -->  piles[j-1] - piles[i-1] + dp[i+1][j-1]`
2. Alex 取 `piles[i-1]`
    1. Lee 取 `piles[j-1]  -->  piles[i-1] - piles[j-1] + dp[i+1][j-1]`
    2. Lee 取 `piles[i]    -->  piles[i-1] - piles[i] + dp[i+2][j]`
    
取四者最大值，即为 `dp[i][j]`

```java
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
```