import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hyman on 17/9/7.
 */
/**********************************************************************************
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 *
 *     For example, given array S = {-1 0 1 2 -1 -4},
 *
 *     A solution set is:
 *     (-1, 0, 1)
 *     (-1, -1, 2)
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {
        //int[] num = {-2, 0, 1, 1, 2};
        //int[] num = {-1,0,1,2,-1,-4};
        int[] num = {-2,0,3,-1,4,0,3,4,1,1,1,-3,-5,4,0};
        System.out.println(new Solution().threeSum(num));
    }


    /**
      * （1）首先对数组进行排序（从小到大）
      * （2）依次取出第 i 个数（i从0开始），并且不重复的选取（跳过重复的数）
      * （3）这样问题就转换为2个数求和的问题（可以用双指针解决方法）
      *  2数求和问题
      *     （4）定义两个指针：左指针（left） 和 右指针（right）
      *     （5）找出固定 left， 此时left所指的位置为数组中最小数，再找到两个数和 不大于 target 的最大 right 的位置
      *     （6）调整 left 的位置（后移），求解和是否为 target O(n)
      *     （7）时间复杂度：O(nlogn) + O(n)
     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        if(nums == null || nums.length < 3) {
            return result;
        }

        Arrays.sort(nums);

        for(int i=0; i<nums.length-2; i++) {

            //避免重复， if(sum + nums[i] == 0)已经考虑了，这里主要是if(sum + nums[i] != 0)的情况
            if(i != 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int left = i+1;
            int right = nums.length-1;


            /**
             * 固定一个数,从后面的数中选出两个数,因为数组是有序的,所以可以
             * 用两个数组下标left和right,left指向当前元素的后一个位置,right指向最后一个位置
             * 三个数相加的和等于0时,加入解集;
             * 小于0时,把left往右边移动;
             * 大于0时,把right往左边移动;
             */
            while(left < right) {

                int sum = nums[left] + nums[right];
                if(sum + nums[i] == 0) {
                    List<Integer> list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);

                    left++;
                    right--;

                    // 跳过后续重复的数字，有可能有多个，所以必须while不能if
                    while(left < right && nums[left] == nums[left-1]) {
                        left++;
                    }

                    while(left < right && nums[right] == nums[right+1]) {
                        right--;
                    }

                } else if(sum + nums[i] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

}
