/**
 * Created by Hyman on 17/8/24.
 */
/**********************************************************************************
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * For example, Given board =
 *
 * [
 *   ["ABCE"],
 *   ["SFCS"],
 *   ["ADEE"]
 * ]
 *
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 **********************************************************************************/
public class Solution {

    public static void main(String[] args) {

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "SEE";

        boolean exist = new Solution().exist(board, word);

        System.out.println(exist);
    }

    public boolean exist(char[][] board, String word) {

        boolean[][] visited = new boolean[board.length][board[0].length];

        // 以每一个位置为起点进行搜索，找到一个路径就停止
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                if(search(board, visited, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @param board   字符矩阵
     * @param visited 访问标记矩阵
     * @param row     访问的行号
     * @param col     访问的列号
     * @param word    匹配的字符串
     * @param start     匹配的位置，取数组是更新后的值可以被其它引用所见
     * @return
     */
    private boolean search(char[][] board, boolean[][] visited, int row, int col, String word, int start) {

        // 如果搜索的位置等于字串的长度，说明已经找到找到匹配的了
        if(start == word.length()) {
            return true;
        }

        boolean hasPath = false;

        // 当前位置合法
        if(check(board, visited, row, col, word, start)) {

            // 标记位置被访问过
            visited[row][col] = true;
            start++;

            // 对上，右，下，左四个方向进行搜索
            hasPath = search(board, visited, row-1, col, word, start)
                    || search(board, visited, row, col+1, word, start)
                    || search(board, visited, row+1, col, word, start)
                    || search(board, visited, row, col-1, word, start);

            // 如果没有找到路径就回溯
            if(!hasPath) {
                visited[row][col] = false;
                start--;

            }
        }

        return hasPath;
    }

    /**
     * 判定访问的位置是否合法
     *
     * @param board   字符矩阵
     * @param visited 访问标记矩阵
     * @param row     访问的行号
     * @param col     访问的列号
     * @param word    匹配的字符串
     * @param start   匹配的位置
     * @return
     */
    private boolean check(char[][] board, boolean[][] visited, int row, int col, String word, int start) {

        if(row >= 0 && row < board.length
                && col >= 0 && col < board[0].length
                && !visited[row][col]
                && board[row][col] == word.charAt(start)) {
            return true;
        }

        return false;
    }


}
