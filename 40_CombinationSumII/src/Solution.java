import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hyman on 17/7/29.
 */
/**********************************************************************************
 *
 * Given a collection of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 *
 * Each number in C may only be used once in the combination.
 *
 * Note:
 *
 * > All numbers (including target) will be positive integers.
 * > Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 *   (ie, a1 ≤ a2 ≤ … ≤ ak).
 * > The solution set must not contain duplicate combinations.
 *
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        //int[] nums = {2,2,2};
        //int target = 4;

        List<List<Integer>> list = new Solution().combinationSum2(nums, target);
        for(int i=0; i<list.size(); i++) {
            for(int k : list.get(i)) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                if(i > start && nums[i] == nums[i-1]) {
                    continue;
                }
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i+1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


}
