/**
 * Created by Hyman on 2016/10/14.
 */

/**********************************************************************************
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node
 * down to the nearest leaf node.
 *
 **********************************************************************************/
public class MinimumDepthOfBinaryTree {

    public static void main(String[] args) {

        new MinimumDepthOfBinaryTree().minDepth(null);

    }

    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        if(root.left == null) {
            return minDepth(root.right) + 1;
        }
        if(root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            this.val = x;
        }
    }
}