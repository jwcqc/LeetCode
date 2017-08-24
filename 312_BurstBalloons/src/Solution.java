/**
 * Created by Hyman on 17/8/23.
 */
/***************************************************************************************
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
 *     number on it represented by array nums.
 *
 *     You are asked to burst all the balloons. If the you burst
 *     balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left
 *     and right are adjacent indices of i. After the burst, the left and right
 *     then becomes adjacent.
 *
 *     Find the maximum coins you can collect by bursting the balloons wisely.
 *
 *     Note:
 *     (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can
 * not burst them.
 *     (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 *     Example:
 *
 *     Given [3, 1, 5, 8]
 *
 *     Return 167
 *
 *     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * Credits:Special thanks to @dietpepsi for adding this problem and creating all test
 * cases.
 *
 *
 * 动态规划数组：
 *      DP[k][h]：nums[k...h]能戳破气球的最大值，表示扎破(k, h)范围内所有气球获得的最大硬币数，不含边界；
 * 递推关系：
 *      取k<m<h，nums[m]假设是最后一个戳破的气球
 *      则DP[k][h] =
 *      for (m = k+1...h)
 *          max(DP[k][m] + DP[m][h] + nums[k] * nums[m] * nums[h]);
 * 初始值：
 *      需要扩展nums，数组长+2，头和尾分别加入1
 *      DP[k][h]:
 *          当k + 1 = h 或 k = h时，为0;
 *          当k + 2 = h 时，为 nums[k] * nums[k+1] * nums[k+2];
 ***************************************************************************************/
public class Solution {

    public static void main(String[] args) {

        int[] nums = {3,1,5,8};

        int result = new Solution().maxCoins(nums);

        System.out.println(result);

    }

    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k) {
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i)
                    dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            }
        }

        return dp[0][n - 1];
    }
}
