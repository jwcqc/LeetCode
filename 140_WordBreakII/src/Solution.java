/**
 * Created by Hyman on 17/7/26.
 */

import java.util.*;

/**********************************************************************************
 *
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence
 * where each word is a valid dictionary word.
 *
 * Return all such possible sentences.
 *
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 *
 * A solution is ["cats and dog", "cat sand dog"].
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        String s = "catsanddog";

        //List<String> result = new Solution().wordBreak_recursive(s, wordDict);
        //List<String> result = new Solution().wordBreak_dp(s, wordDict);
        List<String> result = new Solution().wordBreak_recursive_add_cache(s, wordDict);


        for (String str : result) {
            System.out.println(str);
        }
    }

    /**
     * 使用回溯法，递归会超时
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak_recursive(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        if (wordDict == null || wordDict.size() == 0 || s == null || s.trim().equals("")) {
            return result;
        }

        find(s, wordDict, result, "", 0);
        return result;
    }

    private void find(String s, List<String> wordDict, List<String> result, String item, int start) {
        for (int i = start + 1; i <= s.length(); i++) {
            String part = s.substring(start, i);
            if (wordDict.contains(part)) {
                if (i == s.length()) {
                    item += part;
                    result.add(item);
                } else {
                    String old = item;
                    item += part + " ";
                    find(s, wordDict, result, item, i);
                    item = old;
                }
            }
        }
    }

    /**
     * 解决递归带来的超时问题
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak_recursive_add_cache(String s, List<String> wordDict) {

        Map<String, ArrayList<String>> cache = new HashMap<>();

        return find(s, wordDict, cache);
    }

    private List<String> find(String s, List<String> wordDict, Map<String, ArrayList<String>> cache) {

        if(cache.containsKey(s)) {
            return cache.get(s);
        }

        ArrayList<String> result = new ArrayList<>();
        if(s.length() == 0) {
            result.add("");
            return result;
        }

        for(String str : wordDict) {
            if(s.startsWith(str)) {
                List<String> list = find(s.substring(str.length()), wordDict, cache);
                for(String subStr : list) {
                    result.add(str + (subStr.trim().equals("")?"":" ") + subStr);
                }
            }
        }

        cache.put(s, result);
        return result;
    }


    /**
     * 动态规划的思想
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak_dp(String s, List<String> wordDict) {

        List<String> result = new ArrayList<>();
        List<String> stack = new ArrayList<>();

        if (wordDict == null || wordDict.size() == 0 || s == null || s.trim().equals("")) {
            return result;
        }

        int[][] dp = new int[s.length()][s.length()];

        // 初始化dp数组
        for(int i=0; i<s.length(); i++) {
            for(int j=i; j<s.length(); j++) {
                if(wordDict.contains(s.substring(i, j+1))) {
                    dp[i][j] = 1;
                }
            }
        }

        //从最后一列来进行搜索
        output(s, dp, result, stack, s.length()-1);

        return result;
    }


    private void output(String s, int[][] dp, List<String> result, List<String> stack, int length) {
        //递归结束条件
        if(length == -1) {
            StringBuilder sb = new StringBuilder();
            for(int i=stack.size()-1; i>=0; i--) {
                sb.append(stack.get(i));
                if(i != 0) {
                    sb.append(" ");
                }
            }
            result.add(sb.toString());
        } else {
            for(int k=0; k<=length; k++) {
                //如果某一子串存在字典中
                if(dp != null && dp[k][length] == 1) {
                    stack.add(s.substring(k, length+1));
                    output(s, dp, result, stack, k-1);
                    stack.remove(stack.size()-1);
                }
            }
        }
    }

}
