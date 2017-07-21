/**
 * Created by Hyman on 2017/7/20.
 */

/**********************************************************************************
 *
 * Reverse digits of an integer.
 *
 * Example1: x =  123, return  321
 * Example2: x = -123, return -321
 *
 *
 * Have you thought about this?
 *
 * Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
 *
 * > If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 *
 * > Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer,
 *   then the reverse of 1000000003 overflows. How should you handle such cases?
 *
 * > Throw an exception? Good, but what if throwing an exception is not an option?
 *   You would then have to re-design the function (ie, add an extra parameter).
 *
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {
        int result = new Solution().reverse(-1563847412);
        System.out.println(result);
    }

    public int reverse(int x) {

        if(x == 0) {
            return  0;
        }

        boolean positive = true;
        if(x < 0) {
            positive = false;
            x = -x;
        }

        long result = 0;
        while(x > 0) {
            result *= 10;
            result += x % 10;
            x /= 10;
        }

        if(positive && result > Integer.MAX_VALUE) {
            return 0;
        }

        if(!positive && -result < Integer.MIN_VALUE) {
            return 0;
        }

        if(positive) {
            return (int)result;
        } else {
            return (int)(-result);
        }

    }
}
