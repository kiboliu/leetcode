//Given a binary tree, return the postorder traversal of its nodes' values.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//利用addfirst实现preorder的反向。将应摆在最后的最先加入。因此按root-right-left的顺序addfirst。
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        TreeNode p = root;
        while(!stack.isEmpty() || p != null){
            if(p != null){
                stack.push(p);
                res.addFirst(p.val); //reverse the preorder
                p = p.right; // reverse the preorder
            }else{
                TreeNode node = stack.pop();
                p = node.left;  // reverse the preorder
            }
        }
        return res;
    }
}

//morris traversal,需要建立一个临时节点dump，使其左孩子为root；
//还需要一个子过程，倒序输出cur走至最右端时，cur与cur下一步要回的地方之间的nodes
void reverse(TreeNode from, TreeNode to){
    if(from == to) return;
    TreeNode x = from, y = from.right, z;
    while(true){
        z = y.right;
        y.right = x;
        x = y;
        y = z;
        if(x == to)
            break;
    }
}
void printReverse(TreeNode from, TreeNode to){
    reverse(from, to);
    TreeNode p = to;
    while(true){
        System.out.println(p.val);
        if(p == from)
            break;
        p = p.right;
    }
    reverse(to, from);
}
void postorderMorrisTraversal(TreeNode root){
    TreeNode dump(0);
    dump.left = root;
    TreeNode cur = dump, prev = null;
    while(cur != null){
        if(cur.left == null){
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
                printReverse(cur.left, prev);
                prev.right = null;
                cur = cur.right;
            }
        }
    }
}