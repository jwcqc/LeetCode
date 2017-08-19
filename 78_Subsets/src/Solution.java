/**
 * Created by Hyman on 2017/4/3.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**********************************************************************************
 *
 * Given a set of distinct integers, S, return all possible subsets.
 *
 * Note:
 *
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 *
 * For example,
 * If S = [1,2,3], a solution is:
 *
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 *
 *
 * 此题有几个需要注意的坑：
 * 求的是combination，即“组合” 那么和排列不一样。
 * 两个数的排列有12,13,21,23,31,32 但是组合只有12,13,23 也就是说每次回溯的时候，可以以当前的start节点开始，不用从零开始了！
 * 另外，组合既然是从start开始的，就不需要一个visit数组来记录这个词是否被用过了。(PS:46题就需要)
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> list = new Solution().subsets(nums);
        for(int i=0; i<list.size(); i++) {
            for(int k : list.get(i)) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList)); //千万不能list.add(tempList);
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i+1);
            tempList.remove(tempList.size()-1);
        }
    }

}
