//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//注意list.remove(list.size()-1)，这句话意思是当两条sub recursive code都走完时，回去一步，找另外的结果。
//另一点要注意的时add list时要deep copy一个，不然会指向同一个list，而这个list之后会继续被改变的，并不是生成后就不变了。
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        pathFind(root, 0, sum, list, res);
        return res;
    }
    public void pathFind(TreeNode root, int subSum, int sum, List<Integer> list, List<List<Integer>> res){
        if(root == null) return;
        subSum += root.val;
        list.add(root.val);
        if(root.left == null && root.right == null && subSum == sum){
            res.add(new ArrayList(list));
        }else{
            pathFind(root.left, subSum, sum, list, res);
            pathFind(root.right, subSum, sum, list, res);
        }
        list.remove(list.size() - 1); //backtracking
    }
}