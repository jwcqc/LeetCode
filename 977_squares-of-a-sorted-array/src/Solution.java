import java.util.Arrays;

/**
 * Created by chengbin on 2022-03-23.
 */
public class Solution {


    /**
     * 977. 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     *
     *
     * 示例 1：
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     *
     * 示例 2：
     * 输入：nums = [-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     *
     *
     * 提示：
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums 已按 非递减顺序 排序
     *
     *
     * 进阶：
     * 请你设计时间复杂度为 O(n) 的算法解决本问题
     */


    public int[] sortedSquares(int[] nums) {

        // 由于数组是有序的，且负数平方之后有可能变成最大值，因此数组平方的最大值就在数组的两端

        int left = 0;
        int right = nums.length-1;

        int[] result = new int[nums.length];
        int k = result.length-1;

        while(left <= right) {

            if(nums[left] * nums[left] < nums[right] * nums[right]) {
                result[k] = nums[right] * nums[right];
                right--;
            } else {
                result[k] = nums[left] * nums[left];
                left++;
            }

            k--;
        }

        return result;
    }

    public int[] sortedSquares_by_force(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            nums[i]*= nums[i];
        }

        Arrays.sort(nums);
        return nums;
    }


    public static void main(String[] args) {

        int[] nums = {-4,-1,0,3,10};
//        nums = new int[]{-7,-3,2,3,11};

        Solution solution = new Solution();

        printArray(solution.sortedSquares(nums));
        //printArray(solution.sortedSquares_by_force(nums));

    }

    private static void printArray(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }


}
