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

}
