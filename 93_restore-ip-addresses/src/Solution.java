import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengbin on 2022-03-19.
 */
public class Solution {

    /**
     *
     * 93. 复原 IP 地址
     * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     *
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     *
     *
     * 示例 1：
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     *
     * 示例 2：
     * 输入：s = "0000"
     * 输出：["0.0.0.0"]
     *
     * 示例 3：
     * 输入：s = "101023"
     * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     *
     *
     * 提示：
     * 1 <= s.length <= 20
     * s 仅由数字组成
     */


    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        if(s.length() < 4 || s.length() > 12) {
            return result;
        }

        backtrace(s, result, temp, 0);

        return result;
    }


    private void backtrace(String s, List<String> result, List<String> temp, int start) {

        if(start == s.length()-1 && temp.size() < 3) {
            return;
        }

        if(temp.size() == 4) {
            String join = String.join(".", temp);
            if(join.length() - 3 == s.length()) {
                result.add(join);
            }
            return;
        }

        // 如果剩下的字符串按最长每3个字符串分割一段，分出来的长度大于ip地址剩下可添加的断长度，直接停止往下判断
        if((s.length()-start-1) > (4-temp.size()) * 3) {
            return;
        }


        for(int i = start; i < start+3 && i < s.length(); i++) {

//            String substring = s.substring(start, i + 1);
//            if(!isValid(substring)) {
//                continue;
//            }
            if(!isValid(s, start, i)) {
                continue;
            }

            String substring = s.substring(start, i + 1);
            temp.add(substring);

            backtrace(s, result, temp, i+1);

            temp.remove(temp.size()-1);
        }

    }

    private boolean isValid(String s) {
        if(s.startsWith("0")) {
            return s.length() == 1;
        }
        return Integer.parseInt(s) < 256;
    }

    private boolean isValid(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }

        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }


    public static void main(String[] args) {

        String s = "25525511135";
//        s = "0000";
//        s = "101023";

        Solution solution = new Solution();
        System.out.println(solution.restoreIpAddresses(s));
    }

}
