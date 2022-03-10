/**
 * Created by chengbin on 2022-03-01.
 */
public class Solution {

    /**
     * 416. 分割等和子集
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 示例 1：
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     *
     * 示例 2：
     * 输入：nums = [1,2,3,5]
     * 输出：false
     * 解释：数组不能分割成两个元素和相等的子集。
     *
     */


    /**
     * 背包问题：看数组中是否存在加起来为sum/2的数.
     * 背包容量（V） = sum/2
     * 每一个物品只能装一次,如果出现背包中重量等于sum/2则为true else false
     * dp[i]表示能否填满容量为i的背包
     * 状态转移方程为 dp[i] = dp[i-1] || nums[i]+dp[i-nums[j]]
     * 举例:v = sum/2 = 11   nums = [1,5,11,5]  1是true 0 是false
     *          0  1  2  3  4  5  6  7  8  9  10  11
     *  nums[0] 0  1  0  0  0  0  0  0  0  0   0   0
     *  nums[1] 0  1  0  0  0  1  1  0  0  0   0   0
     *  nums[2] 0  1  0  0  0  1  1  0  0  0   0   1
     *  nums[3] 0  1  0  0  0  1  1  0  0  0   0   1
     *
     * 所以返回true，因为背包中nums[i]的状态都是由上一行决定的因此可以将二维转化为1维数组从尾部开始
     */


    /**
     * 用与运算和移位运算处理偶数判断、除2操作
     *
     * 典型的01背包解题方法，外层遍历背包，内层倒序遍历target
     *
     * 为什么数组要逆序？：因为i每加1代表新的一行开始，由于dp[j-num[i]]每次都得使用的是上一行的数据。
     * 但是如果你正序的话，那么你在计算dp[j]的时候用到的dp[j-num[i]]是本行的，而不是上一行的，所以用逆序，逆序用到的dp[j-num[i]]是上一行的。
     */

    public boolean canPartition(int[] nums) {

        if(nums.length <= 1) {
            return false;
        }

        int sum = 0;
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
        }

        //如果是和为奇数显然无法分成两个等和子集
        /*if(sum % 2 == 1) {
            return false;
        }*/
        int judge = sum & 1;
        if(judge > 0) {
            return false;
        }


        //int target = sum / 2;
        int target =  sum >> 1;

        // 定义dp数组，dp[i]:是否存在子集和为i
        boolean[] dp = new boolean[target+1];

        // 初始化
        dp[0] = true;

        // 确定状态转移公式，开始循环
        for(int i=0; i<nums.length; i++) {
            //从后往前，因为前面的元素我们已经求过了(i>0时确实已经求过了，i==0时我们求一遍即可)，可以直接用
            for(int j=target; j>=nums[i]; j--) {
                dp[j] = dp[j] || dp[j-nums[i]];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {

        int[] nums = {1,5,11,5};
        nums = new int[]{1,2,3,5};


        System.out.println(new Solution().canPartition(nums));

    }
}
