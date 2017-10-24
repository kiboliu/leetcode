//Given preorder and inorder traversal of a tree, construct the binary tree.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//In the array of inorder, it always has a 'root' in each sublist, the left of 'root' is all the left nodes, so does the right;
//In the array of preorder, it always has a 'root' at the beginning of each sublist, so it is easy to find the root; on the right of the root, there are left nodes and right nodes.
//preStart: each sublist 's 'root' 's index.
//inStart, inEnd: each sublist 's beginning and end.
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }
    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++){
            if (inorder[i] == root.val){
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}