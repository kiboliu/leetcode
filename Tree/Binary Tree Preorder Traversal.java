//Given a binary tree, return the preorder traversal of its nodes' values.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//先序遍历，利用stack存储right child
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        Stack<TreeNode> rights = new Stack<TreeNode>();
        while(root != null){
            list.add(root.val);
            if(root.right != null){
                rights.push(root.right);
            }
            root = root.left;
            if(root == null && !rights.isEmpty()){
                root = rights.pop();
            }
        }
        return list;
    }
}

//recursive
public List<Integer> preorderTraversal(TreeNode root) {
	List<Integer> pre = new LinkedList<Integer>();
	if(root==null) return pre;
	pre.add(root.val);
	pre.addAll(preorderTraversal(root.left));
	pre.addAll(preorderTraversal(root.right));
	return pre;
}

//morris traversal
void preorderMorrisTraversal(TreeNode root){
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
                System.out.println(cur.val);
                prev.right = cur;
                cur = cur.left;
            }else{
                prev.right = null;
                cur = cur.right;
            }
        }
    }
}