//Given a binary tree, return the inorder traversal of its nodes' values.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 **/
//中序遍历，当cur.left一直存在时，利用stack存储child; 不存在时则pop()，当pop出了某个右节点不为null时，继续找cur.left
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root;
        while(cur != null || !s.isEmpty()){
            while(cur != null){
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            list.add(cur.val);
            cur = cur.right; 
        }
        return list;
    }
}

// Morris traversal - O(1) space
void inorderMorrisTraversal(TreeNode root){
    TreeNode cur = root, prev = null;
    while(cur != null){
        if(cur.left == null){
            System.out.println(cur.val);
            cur = cur.right;
        }else{
            prev = cur.left;
            while(prev.right != null && prev.right != cur){
                prev = prev.right;
            }
            if(prev.right == null){
                prev.right = cur;
                cur = cur.left;
            }else{
                prev.right = null;
                System.out.println(cur.val);
                cur = cur.right;
            }
        }
    }
}