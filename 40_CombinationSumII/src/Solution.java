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


    /**
     * 语句: if cur > begin and candidates[cur-1] == candidates[cur] 是如何避免重复的：
     *
     * 这个避免重复当思想是在是太重要了。
     * 这个方法最重要的作用是，可以让同一层级，不出现相同的元素。即
     *                   1
     *                  / \
     *                 2   2  这种情况不会发生 但是却允许了不同层级之间的重复即：
     *                /     \
     *               5       5
     *                 例2
     *                   1
     *                  /
     *                 2      这种情况确是允许的
     *                /
     *               2
     *
     * 为何会有这种神奇的效果呢？
     * 首先 cur-1 == cur 是用于判定当前元素是否和之前元素相同的语句。这个语句就能砍掉例1。
     * 可是问题来了，如果把所有当前与之前一个元素相同的都砍掉，那么例二的情况也会消失。
     * 因为当第二个2出现的时候，他就和前一个2相同了。
     *
     * 那么如何保留例2呢？
     * 那么就用cur > begin 来避免这种情况，你发现例1中的两个2是处在同一个层级上的，
     * 例2的两个2是处在不同层级上的。
     * 在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，
     * 必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2。
     * 第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
     */

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                // 要对同一树层使用过的元素进行跳过
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
