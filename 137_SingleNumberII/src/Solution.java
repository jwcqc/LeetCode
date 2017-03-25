/**
 * Created by Hyman on 2017/3/24.
 */

/**********************************************************************************
 *
 * Given an array of integers, every element appears three times except for one. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 *
 **********************************************************************************/
public class Solution {


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 3, 1, 3};
        int result = new Solution().singleNumber(nums);
        System.out.println(result);
    }

    /*
     * 由于除去目标元素target之外，所有元素都出现3次，假设出现3次的元素有n个，
     * 这样的话假如我们统计所有元素的某一位（比如最后一位），其一共有3n+1个二进制位。
     * 因为对与同一个元素来说，其所有的二进制位一定是相同的，所以对这些元素的某一位来说一定是以3个1或3个0为单位出现的，
     * 即3n+1个二进制位中一定是3x个1和3y个0，其中x+y=n，再外加一个target对应的二进制位（1或0都有可能）。
     * 综上所述，我们可以统计所有数字每一位上1的个数，对3取模，如果为1就说明target对应位为1，否则为0。
     * This solution can be easy to extend to "every element appears k times except for one."
     */
    public int singleNumber(int[] nums) {

        int count[] = new int[32];
        int result = 0;

        for(int i=0; i<32; i++) {
            for(int j=0; j<nums.length; j++) {
                if(((nums[j] >> i) & 1) > 0) {
                    count[i]++;
                }
            }
            result |= ((count[i] % 3) << i);
        }

        return result;
    }

}
