//Given two binary trees, write a function to check if they are equal or not.

//Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null) return p==q;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

//进阶版
//Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isSameTree(root.left, root.right);
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null) return p==q;
        return p.val == q.val && isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }
}