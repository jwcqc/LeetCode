/**
 * Created by chengbin on 2022-03-23.
 */
public class Solution {

    /**
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     *
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
     * 如果不存在符合条件的子数组，返回 0 。
     *
     *
     * 示例 1：
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     *
     * 示例 2：
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     *
     * 示例 3：
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     *
     * 提示：
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 105
     *
     * 进阶：
     * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
     */

    /**
     * 参考力扣题解：https://leetcode-cn.com/problems/minimum-size-subarray-sum/solution/javade-jie-fa-ji-bai-liao-9985de-yong-hu-by-sdwwld/
     */

    public int minSubArrayLen(int target, int[] nums) {

        // 滑动窗口

        int left = 0;
        int right = 0;
        int sum = 0;
        int minLen = nums.length+1;

        while(right < nums.length) {

            sum += nums[right];

            while(sum >= target) {

                minLen = Math.min(minLen, right-left+1);

                sum -= nums[left++];

            }

            right++;
        }

        return minLen==nums.length+1 ? 0 : minLen;
    }

    public int minSubArrayLen_by_force(int target, int[] nums) {


        int minLen = nums.length+1;

        for(int i=0; i<nums.length; i++) {

            int sum = 0;

            for(int j=i; j<nums.length; j++) {

                sum += nums[j];

                if(sum >= target) {
                    minLen = Math.min(minLen, j-i+1);
                    break;
                }
            }
        }

        return minLen==nums.length+1 ? 0 : minLen;
    }


    public static void main(String[] args) {

        int target = 7;
        int[] nums = {2,3,1,2,4,3};


        Solution solution = new Solution();

        System.out.println(solution.minSubArrayLen_by_force(target, nums));
        System.out.println(solution.minSubArrayLen(target, nums));

    }

}
