import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chengbin on 2022-03-12.
 */
public class Solution {

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * 示例 2：
     * 输入：digits = ""
     * 输出：[]
     *
     * 示例 3：
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     *
     */

    private final String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {

        StringBuilder temp = new StringBuilder();
        List<String> result = new ArrayList<>();

        if(digits == null || digits.trim().length() == 0) {
            return result;
        }

        backtrace(digits, 0, result, temp);

        return result;
    }

    private void backtrace(String digits, int index, List<String> result, StringBuilder temp) {

        if(index >= digits.length()) {
            result.add(temp.toString());
            return;
        }

        String str = letters[(digits.charAt(index)-'0')];

        for(int i=0; i<str.length(); i++) {

            temp.append(str.charAt(i));

            backtrace(digits, index+1, result, temp);

            temp.deleteCharAt(temp.length() - 1);
        }

    }


    /**
     * 利用队列的先进先出特性
     *
     * from: https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/tong-su-yi-dong-dong-hua-yan-shi-17-dian-hua-hao-m/
     * @param digits
     * @return
     */
    public List<String> letterCombinations_use_queue(String digits) {

        if(digits==null || digits.length()==0) {
            return new ArrayList<String>();
        }
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        String[] letter_map = {
                " ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"
        };
        List<String> res = new ArrayList<>();
        //先往队列中加入一个空字符
        res.add("");
        for(int i=0;i<digits.length();i++) {
            //由当前遍历到的字符，取字典表中查找对应的字符串
            String letters = letter_map[digits.charAt(i)-'0'];
            int size = res.size();
            //计算出队列长度后，将队列中的每个元素挨个拿出来
            for(int j=0;j<size;j++) {
                //每次都从队列中拿出第一个元素
                String tmp = res.remove(0);
                //然后跟"def"这样的字符串拼接，并再次放到队列中
                for(int k=0;k<letters.length();k++) {
                    res.add(tmp+letters.charAt(k));
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {

        String digits = "234";
//        digits = "2";
//        digits = "";

        Solution solution = new Solution();
        System.out.println(solution.letterCombinations(digits));
        System.out.println(solution.letterCombinations_use_queue(digits));

    }


}
