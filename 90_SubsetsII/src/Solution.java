import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hyman on 17/7/28.
 */
/**********************************************************************************
 *
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
 *
 * Note:
 *
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 *
 * For example,
 * If S = [1,2,2], a solution is:
 *
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        List<List<Integer>> list = new Solution().subsetsWithDup(new int[]{1,2,2});

        for(int i=0; i<list.size(); i++) {
            for(int k : list.get(i)) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
}
