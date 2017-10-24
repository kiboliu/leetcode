//Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

//Note: 
//You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

//use the inorder, for the inorder traversal of BST is sorted from low to high.
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0) break;
            root = root.right;
        }
        return root.val;
    }
}