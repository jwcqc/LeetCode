
/**
 * Created by Hyman on 17/7/27.
 */
/**********************************************************************************
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 *
 **********************************************************************************/

/**
 *  Dynamic Programming
 *  -------------------
 *
 *  Define res[i] = the minimum cut from 0 to i in the string.
 *  The result eventually is res[s.size()-1].
 *  We know res[0]=0. Next we are looking for the optimal solution function f.
 *
 *  For example, let s = "leet".
 *
 *  f(0) = 0; // minimum cut of str[0:0]="l", which is a palindrome, so not cut is needed.
 *  f(1) = 1; // str[0:1]="le" How to get 1?
 *                   f(1) might be:  (1)   f(0)+1=1, the minimum cut before plus the current char.
 *                                   (2)   0, if str[0:1] is a palindrome  (here "le" is not )
 *  f(2) = 1; // str[0:2] = "lee" How to get 2?
 *                  f(2) might be:   (1)  f(1) + 1=2
 *                                   (2)  0, if str[0:2] is a palindrome (here "lee" is not)
 *                                   (3)  f(0) + 1,  if str[1:2] is a palindrome, yes!
 *  f(3) = 2; // str[0:3] = "leet" How to get 2?
 *                  f(3) might be:   (1)  f(2) + 1=2
 *                                   (2)  0, if str[0:3] is a palindrome (here "leet" is not)
 *                                   (3)  f(0) + 1,  if str[1:3] is a palindrome (here "eet" is not)
 *                                   (4)  f(1) + 1,  if str[2:e] is a palindrome (here "et" is not)
 *  OK, output f(3) = 2 as the result.
 *
 *  So, the optimal function is:
 *
 *  f(i) = min [  f(j)+1,  j=0..i-1   and str[j:i] is palindrome
 *                0,   if str[0,i] is palindrome ]
 *
 *  The above algorithm works well for the smaller test cases, however for the big cases, it still cannot pass.
 *  Why? The way we test the palindrome is time-consuming.
 *
 *  Also using the similar DP idea, we can construct the look-up table before the main part above,
 *  so that the palindrome testing becomes the looking up operation. The way we construct the table is also the idea of DP.
 *
 *  e.g.   mp[i][j]=true if str[i:j] is palindrome.
 *         mp[i][i]=true;
 *         mp[i][j] = true if str[i]==str[j] and (mp[i+1][j-1]==true or j-i<2 )  j-i<2 ensures the array boundary.
 */

public class Solution {

    public static void main(String[] args) {
        String s = "aaba";
        String s2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        int minCut = new Solution().minCut_1(s2);
        //int minCut = new Solution().minCut_2(s2);

        System.out.println(minCut);
    }


    public int minCut_1(String s) {

        boolean[][] mp = new boolean[s.length()][s.length()];

        for(int i=s.length()-1; i>=0; i--) {
            for(int j=i; j<s.length(); j++) {
                if(s.charAt(i) == s.charAt(j) && (j-i<2 || mp[i+1][j-1])) {
                    mp[i][j] = true;
                } else {
                    mp[i][j] = false;
                }
            }
        }

        int[] dp = new int[s.length()];

        for(int i=0; i<s.length(); i++) {
            if(mp[0][i]) {
                dp[i] = 0;
            } else {
                // if s[0..i] isn't palindrome
                // then, for each 0 to i, find a "j" which meets two conditions:
                //    a) s[j+1..i] are palindrome.
                //    b) res[j]+1 is minimal
                int min = s.length();
                for(int j=0; j<i; j++) {
                    if(mp[j+1][i] && min > dp[j]+1) {
                        min = dp[j] + 1;
                    }
                }
                dp[i] = min;
            }
        }

        return dp[s.length()-1];
    }

    /**
     * 解题思路：动态规划问题。dp[i] - 表示子串（0，i）的最小回文切割，则最优解在dp[s.length-1]中。
     * 分几种情况：
     *   1.初始化：当字串s.substring(0,i+1)(包括i位置的字符)是回文时，dp[i] = 0(表示不需要分割)；否则，dp[i] = i（表示至多分割i次）;
     *   2.对于任意大于1的i，如果s.substring(j,i+1)(j<=i,即遍历i之前的每个子串)是回文时，dp[i] = min(dp[i], dp[j-1]+1);
     *   3.如果s.substring(j,i+1)(j<=i)不是回文时，dp[i] = min(dp[i],dp[j-1]+i+1-j);
     *
     * @param s
     * @return
     */
    public int minCut_2(String s) {

        int[] dp = new int[s.length()];

        for(int i=0; i<s.length(); i++) {

            dp[i] = isPalindrome(s.substring(0, i+1)) ? 0 : i;

            if(dp[i] == 0) {
                continue;
            } else {
                for(int j=1; j<=i; j++) {
                    if(isPalindrome(s.substring(j, i+1))) {
                        dp[i] = Math.min(dp[i], dp[j-1]+1);
                    } else {
                        dp[i] = Math.min(dp[i], dp[j-1] + i - j + 1);
                    }
                }
            }
        }

        return dp[s.length()-1];
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


}
