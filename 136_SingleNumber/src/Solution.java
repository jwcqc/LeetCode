/**
 * Created by Hyman on 2017/3/24.
 */

/**********************************************************************************
 *
 * Given an array of integers, every element appears twice except for one. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {
        //int a[]={1,1,2,2,3,4,4};
        int a[]={1,0,1};
        System.out.println(singleNumber(a));
        System.out.println(1^2);
    }

    /**
     * 利用异或运算的两个特性 1.自己与自己异或结果为0，2.异或满足交换律。
     * 顺序打乱也没有关系
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int len = nums.length;
        int val = 0;
        for(int i=0; i<len; i++) {
            val = val ^ nums[i];
        }
        return val;
    }

    /**
     * 如果重复的数是连续的
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        int len = nums.length;
        for(int i=0; i<len-1; i++) {
            if(nums[i] == nums[i+1]) {
                i++;
            } else {
                return nums[i];
            }
        }
        return nums[len-1];
    }
}
