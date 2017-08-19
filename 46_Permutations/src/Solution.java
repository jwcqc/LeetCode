import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hyman on 2017/7/20.
 */

/**********************************************************************************
 *
 * Given a collection of numbers, return all possible permutations.
 *
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        List<List<Integer>> list = new Solution().permute(new int[]{1,2,3,4});

        for(int i=0; i<list.size(); i++) {
            for(int k : list.get(i)) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<ArrayList<Integer>> permute(int[] num) {

        ArrayList<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> item=new ArrayList<Integer>();

        if(num==null||num.length==0){
            return result;
        }

        boolean []visited=new boolean[num.length];

        permute(num,result,item,visited);

        return result;
    }

    void permute(int[] num,ArrayList<ArrayList<Integer>> result,ArrayList<Integer> item, boolean[] visited) {
        if(item.size()==num.length) {
            result.add(new ArrayList<Integer>(item));
            return;
        }

        for(int i=0;i<num.length;i++) {
            if(!visited[i]){
                item.add(num[i]);
                visited[i]=true;
                permute(num,result,item,visited);
                item.remove(item.size()-1);
                visited[i]=false;
            }
        }
    }


}
