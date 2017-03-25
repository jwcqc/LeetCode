/**
 * Created by Hyman on 2017/3/25.
 */

/**********************************************************************************
 *
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to
 * its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 * Note:
 * The solution is guaranteed to be unique.
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {
        int[] gas = {2, 1, 3};
        int[] cost = {2, 2, 2};

        int result = new Solution().canCompleteCircuit(gas, cost);
        //int result = new Solution().canCompleteCircuit2(gas, cost);
        System.out.println(result);
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length;
        for (int i = 0; i < n; i++) {

            if (gas[i] - cost[i] >= 0) {

                int j = i;
                int count = 0;
                int remain = 0;

                while (count < n) {

                    if (j >= n) {
                        j = j - n;
                    }

                    remain += gas[j] - cost[j];
                    if (remain < 0) {
                        break;
                    }

                    j++;
                    count++;
                }

                if (count == n) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 从start出发，如果油量足够，可以一直向后走 end++；油量不够的时候，
     * start向后退，最终start == end的时候，如果有解一定是当前 start所在位置
     * 思路from牛客网@牛客923
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {

        int start = gas.length - 1;
        int end = 0;
        int sum = gas[start] - cost[start];

        while(start > end) {

            if(sum >= 0) {
                sum += gas[end] - cost[end];
                end++;
            } else {
                sum += gas[start] - cost[start];
                start--;
            }
        }

        return sum >= 0 ? start : -1;
    }


}
