//Given a binary tree, determine if it is height-balanced.

//For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//According to the definition of the balanced binary tree, check the difference between the heights of two sub trees is no more than 1.
//Scan two times, the complexity is O(N2)
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    public int depth(TreeNode root){
        if(root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
//bottom-up dfs, just scan once, the complexity is O(N)
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }
    public int dfsHeight(TreeNode root){
        if(root == null) return 0;
        int leftHeight = dfsHeight(root.left);
        if(leftHeight == -1) return -1;
        int rightHeight = dfsHeight(root.right);
        if(rightHeight == -1) return -1;
        if(Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
