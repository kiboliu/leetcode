//Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

//Start by noting that 1...n is the in-order traversal for any BST with nodes 1 to n. 
//So if pick up i-th node as the root, 
//the left subtree will contain elements 1 to (i-1),
//and the right subtree will contain elements (i+1) to n.
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
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        if(n == 0) return res;
        return genTrees(1,n);
    }
    public List<TreeNode> genTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        if(start > end){
            list.add(null);
            return list;
        }
        List<TreeNode> left,right;
        for(int i = start; i <= end; i++){
            left = genTrees(start, i-1);
            right = genTrees(i+1, end);
            for(TreeNode lnode : left){
                for(TreeNode rnode : right){
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    list.add(root);
                }
            }
        }
        return list;
    }
}