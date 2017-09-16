/**
 * Created by Hyman on 17/9/15.
 */
/**********************************************************************************
 *
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned
 * in the top-left room and must fight his way through the dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
 * For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path
 * RIGHT-> RIGHT -> DOWN -> DOWN.
 *
 *
 *   +------+------+------+
 *   |      |      |      |
 *   | -2(K)|  -3  |  -3  |
 *   |      |      |      |
 *   +--------------------+
 *   |      |      |      |
 *   |  -5  | -10  |   1  |
 *   |      |      |      |
 *   +--------------------+
 *   |      |      |      |
 *   |  10  |  30  | -5(P)|
 *   |      |      |      |
 *   +------+------+------+
 *
 *
 * Notes:
 *
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups,
 * even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 * Credits:Special thanks to @stellari for adding this problem and creating all test cases.
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        int[][] dungeon = {
                {-2, -3, -3},
                {-5, -10, 1},
                {10, 30, -5}
        };

        int result = new Solution().calculateMinimumHP(dungeon);
        System.out.println(result);

    }


    /**
     * 思路：在走完最后一个房间的时候血量至少要剩下１，因此最后的状态可以当成是初始状态，
     * 由后往前依次决定在每一个位置至少要有多少血量, 这样一个位置的状态是由其下面一个和和左边一个的较小状态决定 ．
     * 因此一个基本的状态方程是: dp[i][j] = min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j].
     * 但是还有一个条件就是在每一个状态必须血量都要大于１，因此我们还需要一个方程在保证在每一个状态都大于１，
     * 即：dp[i][j] = max(dp[i][j], 1);　也就是说虽然当前的血量到最后能够剩下１，但是现在已经低于１了，我们需要为其提升血量维持每一个状态至少都为１．
     * 这样我们需要一个二维数组在记录我们需要的之前的状态，也就是一个位置的下方和左方的状态．而其下方的状态可以存储在其当前位置，因此我们可以做状态压缩，只需要一维的数组．
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {

        int row = dungeon.length;
        int col = row > 0 ? dungeon[0].length : 0;

        if(row <= 0 || col <= 0) {
            return 0;
        }

        int[][] dp = new int[row+1][col+1];
        dp[row][col-1] = 1;  // or dp[row-1][col] = 1;

        for(int i=row-1; i>=0; i--) {
            for(int j=col-1; j>=0; j--) {
                int val = Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j];
                dp[i][j] = Math.max(val ,1);
            }
        }

        return dp[0][0];
    }
}
