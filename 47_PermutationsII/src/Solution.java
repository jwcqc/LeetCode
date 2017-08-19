import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hyman on 17/7/28.
 */
/**********************************************************************************
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        //int[] arr = new int[] {1,1,2};
        int[] arr = new int[] {3,3,0,3};

        List<List<Integer>> list = new Solution().permuteUnique(arr);

        for(int i=0; i<list.size(); i++) {
            for(int k : list.get(i)) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> item = new ArrayList<Integer>();

        if(nums==null||nums.length==0){
            return result;
        }

        boolean []visited=new boolean[nums.length];

        Arrays.sort(nums);  // Sort the array to make sure we can skip the same value. 这一步必须

        permute(nums,result,item,visited);

        return result;
    }


    void permute(int[] nums,List<List<Integer>> result,List<Integer> item, boolean[] visited) {
        if(item.size()==nums.length) {
            result.add(new ArrayList<Integer>(item));
            return;
        }

        for(int i=0;i<nums.length;i++) {
            //when a number has the same value with its previous, we can use this number only if his previous is used
            if(visited[i] || (i > 0 && nums[i] == nums[i-1] && !visited[i - 1])) continue;
            if(!visited[i]){
                item.add(nums[i]);
                visited[i]=true;
                permute(nums,result,item,visited);
                item.remove(item.size()-1);
                visited[i]=false;
            }
        }
    }

}
