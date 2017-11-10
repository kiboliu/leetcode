//You are given a binary tree in which each node contains an integer value.

//Find the number of paths that sum to a given value.

//The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

//The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//相当简洁的递归，计算该节点为起点的path数目，然后加上以两个child为起点的path树；
//求当前节点的path数时也十分简洁！
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) 
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }
}