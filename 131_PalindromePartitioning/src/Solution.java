import java.util.ArrayList;
import java.util.List;

/**
 *  * Created by Hyman on 2016/10/18
 */

/**********************************************************************************
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * For example, given s = "aab",
 *
 * Return
 *
 *   [
 *     ["aa","b"],
 *     ["a","a","b"]
 *   ]
 *
 **********************************************************************************/
public class Solution {


    public static void main(String[] args) {

        String s = "aaba";

        List<List<String>> result = partition(s);

        print(result);
    }


    public static List<List<String>> partition(String s) {

        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();

        if(s == null || s.isEmpty()) {
            result.add(list);
            return result;
        }

        doPartition(s, 0, list, result);

        return result;
    }

    private static void doPartition(String s, int start, List<String> list, List<List<String>> result) {
        // 回溯返回条件
        if(start == s.length()) {
            // 这里不new一个list而是直接用参数传进来的list的话，
            // 会由于引用而被后续list.remove(list.size()-1)语句全部清除掉，导致result中全为空
            List<String> tmp = new ArrayList<>(list);
            result.add(tmp);
            return;
        }

        for(int i=start; i<s.length(); i++) {

            String sub = s.substring(start, i+1);

            if(isPalindrome(sub)) {
                list.add(sub);

                doPartition(s, i+1, list, result);

                //移除最外面的字符，方便i++之后判断更长的字符
                // 移除刚刚添加的元素，也就是回到之前的状态，以便走其他分支串
                list.remove(list.size()-1);
            }
        }
    }

    /**
     * 判断给定的字符串是否回文
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {

        if(s.length() == 1) {
            return true;
        }

        int begin = 0, end = s.length()-1;
        while(begin < end) {
            if(s.charAt(begin) == s.charAt(end)) {
                begin++;
                end--;
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 打印结果
     * @param list
     */
    private static void print(List<List<String>> list) {

        if(list == null || list.isEmpty()) {
            return;
        }

        for(int i=0; i<list.size(); i++) {
            List<String> tmp = list.get(i);
            if(tmp == null) {
                continue;
            }

            for(int j=0; j<tmp.size(); j++) {
                System.out.print(tmp.get(j) + " ");
            }
            System.out.println();
        }

    }
}
