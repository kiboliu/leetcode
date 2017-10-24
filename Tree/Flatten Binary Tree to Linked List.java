//Given a binary tree, flatten it to a linked list in-place.
//flatten means make it to be a line, all nodes are in the right place.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 //这个方法太尼玛厉害了！bottom-up，然后一点点连起来，prev从最后一个元素开始连，一步步和之前的元素相连
class Solution {
    private TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

// non-recursion method.这个方法更好理解，将右child移成左child的sub-rightchild，然后将整个左child移到右边，依次循环
class Solution {
    public void flatten(TreeNode root) {
        TreeNode now = root;
        while(now != null){
            if(now.left != null){
                TreeNode pre = now.left;
                while(pre.right != null){
                    pre = pre.right;
                }
                pre.right = now.right;
                now.right = now.left;
                now.left = null;
            }
            now = now.right;
        }
    }
}
//利用了morris traversal的思想
