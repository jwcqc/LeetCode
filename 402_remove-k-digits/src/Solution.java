import java.util.Stack;

/**
 * Created by chengbin on 2022-02-24.
 */
public class Solution {

    /**
     * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
     *
     * 链接：https://leetcode-cn.com/problems/remove-k-digits/
     *
     *
     * 一些用例：
     *  "112"，1，预期结果：11
     *  "1432219"， 3，预期结果：1219
     *  "10200"，1，预期结果：200
     *  "10"，2，预期结果：0
     *  "10001"，4，预期结果：0
     *
     */

    public String removeKdigits(String num, int k) {


        if(num == null || num.length() <= k) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<num.length(); i++) {
            while(!stack.isEmpty() && k > 0 && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder stringBuilder = new StringBuilder();
        while(!stack.empty()) {
            stringBuilder.append(stack.pop());
        }
        stringBuilder.reverse();
        int i = 0;

        for (; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) != '0') break;
        }
        String result = stringBuilder.substring(i);
        return result.length() == 0 ? "0" : result;
    }



    public String removeKdigits3(String num, int k) {
        int len = num.length();
        if(len == k){
            return "0";
        }
        char[] arr = num.toCharArray();
        char[] res = new char[len];
        int top=-1;
        int n = len-k;
        for(char c : arr){
            while(k > 0 && top != -1 && res[top] > c){
                top--;
                k--;
            }
            res[++top] = c;
        }
        StringBuilder ans = new StringBuilder();
        for(int i=0; i<n; i++){
            if(ans.length()==0 && res[i] == '0'){
                continue;
            }
            ans.append(res[i]);
        }

        return ans.length()==0 ? "0" : ans.toString();
    }

    public String removeKdigits4(String nums, int k) {
        Stack<Character> s = new Stack<>();
        StringBuilder sb = new StringBuilder(nums.length() - k);
        for (int i = 0; i < nums.length(); i++) {
            while (!s.isEmpty() && s.peek() > nums.charAt(i) && k > 0) {
                s.pop();
                k--;
            }
            s.push(nums.charAt(i));
        }
        while (k > 0) {
            s.pop();
            k--;
        }
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }

        //取出前缀0
        String st = sb.reverse().toString().trim();
        int i = 0;
        for (; i < st.length(); i++) {
            if (st.charAt(i) != '0') break;
        }
        st = st.substring(i);
        return st.length() == 0 ? "0" : st;
    }


    public static void main(String[] args) {
        String num = "1432219";
//        num = "10200";
//        num = "1234567890";
//        num = "112";
        num = "10001";

        int k = 1;
//        k = 3;
//        k = 9;
        k = 4;

        System.out.println(new Solution().removeKdigits(num, k));

        // 下面是参考网友的
        System.out.println(new Solution().removeKdigits3(num, k));
        System.out.println(new Solution().removeKdigits4(num, k));
    }
}


