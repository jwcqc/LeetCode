/**
 * Created by Hyman on 17/7/29.
 */

import java.util.ArrayList;
import java.util.List;

/**********************************************************************************
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * For example,
 * If n = 4 and k = 2, a solution is:
 *
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        List<List<Integer>> list = new Solution().combine(4, 2);

        for(int i=0; i<list.size(); i++) {
            for(int k : list.get(i)) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (k == 0)
            return result;
        List<Integer> track = new ArrayList<>();
        backtrack(result, track, 1, n, k);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> track, int m, int n, int k) {
        if (track.size() == k) {
            List<Integer> t = new ArrayList<>(track);
            result.add(t);
            return;
        }
        for (int i = m; i <= n; i++) {
            track.add(i);
            backtrack(result, track, i + 1, n, k);
            track.remove(track.size() - 1);
        }
    }

}
