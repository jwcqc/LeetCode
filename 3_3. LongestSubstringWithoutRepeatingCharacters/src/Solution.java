/**
 * Created by Hyman on 17/8/24.
 */

import java.util.HashMap;
import java.util.Map;

/**********************************************************************************
 *
 * Given a string, find the length of the longest substring without repeating characters.
 * For example:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        //String s = "abcabcbb";
        //String s = "bbbbb";
        String s = "pwwkew";
        //String s = "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco";
        //String s = "qopubjguxhxdipfzwswybgfylqvjzhar";

        new Solution().lengthOfLongestSubstring(s);

    }

    public int lengthOfLongestSubstring(String s) {

        // 字符串输入不合法
        if (s == null) {
            return 0;
        }

        // 当前处理的开始位置
        int start = 0;
        // 记录到的最大非重复子串长度
        int result = 0;
        // 访问标记，记录最新一次访问的字符和位置
        Map<Character, Integer> map = new HashMap<>(s.length());

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 如果字符已经出现过(在标记开位置算起)，就重新标记start
            if (map.containsKey(ch) && map.get(ch) >= start) {
                start = map.get(ch) + 1;
            }
            // 如果没有出现过就求最大的非重复子串的长度
            else {
                result = Math.max(result, i - start + 1);
            }

            // 更新访问记录
            map.put(ch, i);
        }

        return result;
    }

}
