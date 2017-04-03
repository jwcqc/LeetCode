/**
 * Created by Hyman on 2016/10/18.
 */

import java.util.HashSet;
import java.util.Set;

/**********************************************************************************
 *
 * Given a string s and a dictionary of words dict, determine if s can be segmented
 * into a space-separated sequence of one or more dictionary words.
 *
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 *
 *
 * possible[i] = true      if S[0,i]在dictionary里面
 *             = true      if possible[k] == true 并且 S[k+1,i]在dictionary里面， 0<k<i
 *             = false      if no such k exist
 *
 **********************************************************************************/
public class WordBreak {

    public static void main(String[] args) {

        Set<String> wordDict = new HashSet<>();
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println(wordBreak("leetcode", wordDict));
    }

    /**
     * 动态规划
     * 状态转移方程：f(i) 表示s[0,i]是否可以分词，
     * f(i) = f(j) && f(j+1,i); 0 <= j < i;
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, Set<String> wordDict) {

        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;

        /*for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }*/

        for(int i=0; i<len; i++){
            if(dp[i]) {
                for(int j=i; j<len; j++){
                    if(wordDict.contains(s.substring(i,j+1))) {
                        dp[j+1] = true;
                    }
                }
            }
        }

        return dp[len];
    }

    /**
     * dfs
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak2(String s, Set<String> wordDict) {

        int n = s.length();
        int[][] arr = new int[n+1][n+1];

        dfs(s, 0, wordDict, arr);


        return false;
    }

    private static boolean dfs(String s, int start, Set<String> wordDict, int[][] arr) {

        int n = s.length();
        for(int i=start; i<n; i++) {



        }


        return false;
    }


}
