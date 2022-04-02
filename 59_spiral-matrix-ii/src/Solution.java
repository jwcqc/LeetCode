/**
 * Created by chengbin on 2022-04-02.
 */
public class Solution {

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     *
     * 示例 1：
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：[[1]]
     *  
     *
     * 提示：
     * 1 <= n <= 20
     *
     */

    public int[][] generateMatrix(int n) {

        /**
         * 基本就是由外向内一圈一圈这么画下去
         *
         * 注意：
         * 一圈下来，我们要画四条边，这四条边怎么画，每画一条边都要坚持一致的左闭右开，或者左开又闭的原则，这样这一圈才能按照统一的规则画下来。
         * 下面代码就没有按照上述原则处理，而是运行的时候发现各种问题，然后各种修修补补
         *
         * 如果在画每一条边的时候，一会左开又闭，一会左闭右闭，一会又来左闭右开，岂能不乱。。。
         *
         */

        int left = 0;
        int right = n-1;

        int top = 0;
        int bottom = n-1;

        int value = 1;

        int[][] matrix = new int[n][n];

        // 以顺时针顺序填入所有元素

        while(left <= right && top <= bottom) {

            for(int i=left; i<=right; i++) {
                matrix[top][i] = value;
                value++;
            }

            for(int i=top+1; i<=bottom; i++) {
                matrix[i][right] = value;
                value++;
            }

            for(int i=right-1; i>=left; i--) {
                matrix[bottom][i] = value;
                value++;
            }

            for(int i=bottom-1; i>=top+1; i--) {
                matrix[i][left] = value;
                value++;
            }

            left++;
            right--;
            top++;
            bottom--;

        }

//        while(left <= right && top <= bottom) {
//
//            for(int i=left; i<=right-1; i++) {
//                matrix[top][i] = value;
//                value++;
//            }
//
//            for(int i=top; i<=bottom-1; i++) {
//                matrix[i][right] = value;
//                value++;
//            }
//
//            for(int i=right; i>=left+1; i--) {
//                matrix[bottom][i] = value;
//                value++;
//            }
//
//            for(int i=bottom; i>=top+1; i--) {
//                matrix[i][left] = value;
//                value++;
//            }
//
//            left++;
//            right--;
//            top++;
//            bottom--;
//
//        }
//
//        // 上面注释的那一段不需要用这个逻辑
//        // 如果n为奇数的话，需要单独给矩阵最中间的位置赋值
//        if (n % 2 != 0) {
//            matrix[n/2][n/2] = value;
//        }

        return matrix;
    }


    public static void main(String[] args) {

        int n=4;
        n=3;

        Solution solution = new Solution();

        printArray(solution.generateMatrix(n));

    }


    private static void printArray(int[][] nums) {
        for(int i=0; i<nums[0].length; i++) {
            for(int j=0; j<nums[i].length; j++) {
                System.out.print(nums[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
