/**
 * Created by Hyman on 17/7/28.
 */
/**********************************************************************************
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 *
 **********************************************************************************/
public class Solution {


    public static void main(String[] args) {

        int steps = new Solution().climbStairs(3);

        System.out.println(steps);
    }

    /**
     * Here are the steps to get the solution incrementally.
     *
     * Base cases: if n <= 0, then the number of ways should be zero. if n == 1, then there is only way to climb the stair.
     * if n == 2, then there are two ways to climb the stairs. One solution is one step by another; the other one is two steps at one time.
     *
     * The key intuition to solve the problem is that given a number of stairs n, if we know the number ways to get to the
     * points [n-1] and [n-2] respectively, denoted as n1 and n2 , then the total ways to get to the point [n] is n1 + n2.
     * Because from the [n-1] point, we can take one single step to reach [n].
     * And from the [n-2] point, we could take two steps to get there.
     * There is NO overlapping between these two solution sets, because we differ in the final step.
     * @param n
     * @return
     */
    public int climbStairs(int n) {

        if(n <= 2) {
            return n;
        }

        int[] step = new int[n+1];
        step[0] = 0;
        step[1] = 1;
        step[2] = 2;

        for(int i=3; i<=n; i++) {
            step[i] = step[i-1]+step[i-2];
        }

        return step[n];
    }
}
