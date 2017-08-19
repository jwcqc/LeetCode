/**
 * Created by Hyman on 17/7/28.
 */
/**********************************************************************************
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach
 * the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 *
 *    start                                                  
 *    +---------+----+----+----+----+----+
 *    |----|    |    |    |    |    |    |
 *    |----|    |    |    |    |    |    |
 *    +----------------------------------+
 *    |    |    |    |    |    |    |    |
 *    |    |    |    |    |    |    |    |
 *    +----------------------------------+
 *    |    |    |    |    |    |    |----|
 *    |    |    |    |    |    |    |----|
 *    +----+----+----+----+----+---------+
 *                                   finish
 *
 *
 * How many possible unique paths are there?
 *
 * Above is a 3 x 7 grid. How many possible unique paths are there?
 *
 * Note: m and n will be at most 100.
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        int paths = new Solution().uniquePaths(3, 7);

        System.out.println(paths);

    }

    /**
     * Dynamic Programming
     *
     * We have a dp[i][j] represents  how many paths from [0][0] to hear. So, we have the following DP formuler:
     *
     *    dp[i][j] =  1  if i==0 || j==0        //the first row/column only have 1 unique path.
     *             =  dp[i-1][j] + dp[i][j-1]   //the path can be from my top cell and left cell.
     */
    public int uniquePaths(int m, int n) {

        if(m <= 0  || n <= 0) {
            return 0;
        }


        int[][] dp = new int[m][n];

        for(int i=0; i<m; i++) {
            dp[i][0] = 1;
        }

        for(int j=0; j<n; j++) {
            dp[0][j] = 1;
        }

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

}
