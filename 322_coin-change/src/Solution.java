import java.util.Arrays;

/**
 * Created by chengbin on 2022-02-26.
 */
public class Solution {

    /**
     *
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     *
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     *
     * 你可以认为每种硬币的数量是无限的
     *
     * 链接：https://leetcode-cn.com/problems/coin-change
     *
     *
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     *
     */

    public int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 或者由于amount + 1 是不可能达到的换取数量，于是使用其进行填充
        // Arrays.fill(dp, amount+1);

        dp[0] = 0;
        for(int i=0; i<coins.length; i++) {
            for(int j=coins[i]; j<=amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    // 包含当前的coins[i]，那么剩余钱就是j-coins[i]，这种情况要兑换的硬币数是dp[j-coins[i]] + 1
                    // 如果不包含当前的coins[i]，要兑换的硬币数就是dp[j]
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    int minCount = Integer.MAX_VALUE;
    public int coinChangeUseDF(int[] coins, int amount) {
        Arrays.sort(coins);
        recursion(coins, amount, 0, coins.length - 1);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    /**
     * 1、按金额从大到小，从多到少（排序，用余数一步到位）
     * 2、预判低于最优解，终止递归（可以返回最优解，不过提升有限，意义不大）
     * 3、能整除即可返回
     */
    private void recursion(int[] coins, int amount, int count, int index) {
        if (index < 0 || count + amount / coins[index] >= minCount) return;
        if (amount % coins[index] == 0) {
            minCount = Math.min(minCount, count + amount / coins[index]);
            return;
        }
        for (int i = amount / coins[index]; i >= 0; i--) {
            recursion(coins, amount - i * coins[index], count + i, index - 1);
        }
    }

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println(new Solution().coinChange(coins, amount));
        System.out.println(new Solution().coinChangeUseDF(coins, amount));

    }
}
