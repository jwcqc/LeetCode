/**
 * Created by Hyman on 17/8/25.
 */
/**********************************************************************************
 *
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * Credits:Special thanks to @mithmatt for adding this problem and creating all test cases.
 *
 **********************************************************************************/
public class Solution {


    public static void main(String[] args) {

        int[] nums = {3,2,1,5,6,4};

        int kthLargest = new Solution().findKthLargest(nums, 2);

        System.out.println(kthLargest);

    }

    public int findKthLargest(int[] nums, int k) {

        int low = 0;
        int high = nums.length-1;
        int index = nums.length - k;

        while(true) {
            int pivot = partition(nums, low, high);
            if(pivot == index) {
                return nums[pivot];
            } else if(pivot < index) {
                low = pivot+1;
            } else {
                high = pivot-1;
            }
        }
    }

    /**
     * 顺便写一下快排算法
     * @param nums
     * @param low
     * @param high
     */
    private void quickSort(int[] nums, int low, int high) {
        if(low < high) {
            int pivot = partition(nums, low, high);
            quickSort(nums, low, pivot-1);
            quickSort(nums, pivot+1, high);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while(low < high) {

            while(low < high && nums[high] >= pivot) {
                high--;
            }
            nums[low] = nums[high];

            while(low < high && nums[low] <= pivot) {
                low++;
            }
            nums[high] = nums[low];
        }

        nums[low] = pivot;
        return low;
    }
}
