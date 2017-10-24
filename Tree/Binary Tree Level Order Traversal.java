//Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).


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
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        
        if(root == null) return list;
        
        queue.offer(root);
        while(!queue.isEmpty()){
        	//当前queue内有的，即上一层的元素个数
            int levelNum = queue.size();
            List<Integer> sublist = new LinkedList<Integer>();
            //由上一层的元素得到这一层的元素并存入队列中
            for(int i = 0; i < levelNum; i++){
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                //上一层的元素利用完毕，poll出queue并存入上一层的sublist中
                sublist.add(queue.poll().val);
            }
            list.add(sublist);
        }
        return list;
    }
}

//附加：return the bottom-up level order traversal
//法一： 将上述结果反向，即改掉这一句: list.add(sublist) --- list.add(0, sublist)
//法二：DFS：
public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
    levelMaker(wrapList, root, 0);
    return wrapList;
}

public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
    if(root == null) return;
    if(level >= list.size()) {
        list.add(0, new LinkedList<Integer>());
    }
    levelMaker(list, root.left, level+1);
    levelMaker(list, root.right, level+1);
    list.get(list.size()-level-1).add(root.val);
}
