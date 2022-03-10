import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chengbin on 2022-03-03.
 */
public class Solution {

    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     *
     * 说明：
     * 所有数字都是正整数。
     * 解集不能包含重复的组合。 
     *
     * 示例 1:
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     *
     * 示例 2:
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     *
     */


    public List<List<Integer>> combinationSum3(int k, int n) {
        if(k >= n) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        int sum = 0;
        backtrace(result, list, n, k, 1, sum);

        return result;
    }


    private void backtrace(List<List<Integer>> result, List<Integer> list, int n, int k, int start, int sum) {

        if(sum > n) {  //剪枝
            return;
        }

        if(list.size()==k) {
            if(sum == n) {
                result.add(new ArrayList<>(list));
                //list = new ArrayList<>();
            }
            return;
        }

        //for(int i=start; i<=9; i++) {
        for(int i=start; i<=9-(k-list.size())+1; i++) {

            list.add(i);
            sum += i;

            backtrace(result, list, n, k, i+1, sum);

            list.remove(Integer.valueOf(i));
            sum -= i;
        }

    }

    public static void main(String[] args) {

        int k = 3;
        int n = 9;

        List<List<Integer>> list = new Solution().combinationSum3(k, n);

        for(int i=0; i<list.size(); i++) {
            List<Integer> inner = list.get(i);
            for(int j=0; j<inner.size(); j++) {
                System.out.print(inner.get(j) + " ");
            }
            System.out.println();
        }

    }

}
