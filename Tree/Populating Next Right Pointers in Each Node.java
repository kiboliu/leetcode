//Given a binary tree, populate each next pointer to point  its next right node. If there is no next right node, the next pointer should be set to NULL.
//Initially, all next pointers are set to NULL.
//Only use constant extra space.
//Assume it is a perfect binary tree.(all leaves are at the same level, and every parent has two children)

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

//每一层都从左开始走，cur在横向移动，同时更新cur下一层的nodes的next.
public class Solution {
	public void connect(TreeLinkNode root) {
		TreeLinkNode level_start = root;
		while(level_start != null) {
			TreeLinkNode cur = level_start;
			while(cur != null) {
				if(cur.left != null) cur.left.next = cur.right;
				if(cur.right != null && cur.next != null) cur.right.next = cur.next.left;
				cur = cur.next;
			}
			level_start = level_start.left;
		}
	}
}

//进阶版: if the given tree could be any tree.
public class Solution {
	public void connect(TreeLinkNode root) {
		TreeLinkNode cur = root;
		while(cur != null) {
			TreeLinkNode level_start = null;
            TreeLinkNode pre = null;
			while(cur != null) {
                if(level_start == null){
                    level_start = cur.left != null? cur.left : cur.right;
                }
				if(cur.left != null){
                    if(pre != null) pre.next = cur.left;
                    pre = cur.left;
                }
				if(cur.right != null){
                    if(pre != null) pre.next = cur.right;
                    pre = cur.right;
                }
				cur = cur.next;
			}
			cur = level_start;
		}
	}
}