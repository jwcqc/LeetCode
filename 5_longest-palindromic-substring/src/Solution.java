/**
 * Created by chengbin on 2022-03-15.
 */
public class Solution {

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     *
     * 示例 1：
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     *
     * 示例 2：
     * 输入：s = "cbbd"
     * 输出："bb"
     *
     *
     * 提示：
     * * 1 <= s.length <= 1000
     * * s 仅由数字和英文字母组成
     */

    /**
     * 力扣官方题解：
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     */


    /**
     * 动态规划法
     */
    public String longestPalindrome(String s) {

        if(s.length() == 1) {
            return s;
        }

        int maxLen = 1;
        int start = 0;

        boolean[][] dp = new boolean[s.length()][s.length()];

        // j从左往右驱动，i从上往下驱动，只需要考虑为二维数组的右上角赋值，因为i<=j;
        for(int j=0; j<s.length(); j++) {

            for(int i=0; i<=j; i++) {

                if(s.charAt(i) != s.charAt(j)) {

                    dp[i][j] = false;

                } else {

                    // j=i+1和j=i+2两种情况，且最左和最右字母相等的情况，如aa, aba
                    if(j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                // 在得到[i,j]区间是回文子串的时候，直接保存最长回文子串的左边界和右边界
                if(dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }

            }
        }

        return s.substring(start, start+maxLen);
    }

    /**
     * 中心扩散
     *
     * 时间复杂度： O(n^2) 两个for循环
     * 空间复杂度： O(1) 不需要额外的空间
     */
    private String longestPalindrome_by_spread(String s) {

        if(s.length() == 1) {
            return s;
        }

        int maxLen = 1;
        int start = 0;

        for(int i=0; i<s.length()-1; i++) {

            int len1 = spreadAround(s, i, i);   //考虑s的长度为奇数
            int len2 = spreadAround(s, i, i+1); //考虑s的长度为偶数

            int len = Math.max(len1, len2);

            if(len > maxLen) {
                maxLen = len;

                // 根据i和maxLen算start下标
                // 奇数：i-maxLen/2
                // 偶数：i-maxLen/2+1
                // 统一：i-(maxLen-1)/2
                start = i - (maxLen - 1) / 2;
            }
        }

        return s.substring(start, start + maxLen);
    }

    /**
     * 返回检查到的回文串长度
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int spreadAround(String s, int left, int right) {

        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // 减2是因为在while循环里left和right已经分别往左和右多走了一步不满足条件才退出，如left可能变成-1，right变成了s.length
        return right - left - 2 + 1;
    }

    /**
     * 暴力解法
     *
     * 时间复杂度：两层 for 循环 O(n²），for 循环里边判断是否为回文 O(n），所以时间复杂度为 O(n³）。
     * 空间复杂度：O(1），常数个变量。
     */
    private String longestPalindrome_by_force(String s) {
        if(s.length() == 1) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        for(int i=0; i<s.length()-1; i++) {
            for(int j=i+1; j<s.length(); j++) {
                if(j-i+1 > maxLen && isPalindrome(s, i, j)) {
                    maxLen = j - i +1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }


    /**
     * 判断给定的字符串是否回文
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s, int begin, int end) {

        if(s.length() == 1) {
            return true;
        }

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

    public static void main(String[] args) {

        String s = "babad";
//        s = "aacdefcaa";

        Solution solution = new Solution();

        System.out.println(solution.longestPalindrome_by_force(s));
        System.out.println(solution.longestPalindrome_by_spread(s));
        System.out.println(solution.longestPalindrome(s));

    }
}
