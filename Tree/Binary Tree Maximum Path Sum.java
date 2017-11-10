//Given a binary tree, find the maximum path sum.

//For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
//The path must contain at least one node and does not need to go through the root.

//使用DFS来遍历。 可以理解成从最底部开始往上走，逐步更新maxValue：
//对于一个leaf而言，它的pathsum也就是本身，因此null node的值为0；
//对于一个有左右child的节点而言，它对pathsum的贡献，应是max（左边，右边）+自己；
//此时就要考虑，如果左边/右边小于0的话，对整个pathsum是负作用的，应当不加；
//至于更新maxValue的值，则在每次计算出一个节点的左边，右边后，将maxValue和left+right+node.val进行对比；
//如果在这个节点产生了更大的maxValue，则更新。
//智商的差距！！！qaq
class Solution {
	int maxValue;

	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
		maxPathDown(root);
		return maxValue;
	}

	private int maxPathDown(TreeNode node) {
		if (node == null) return 0;
		int left = Math.max(0, maxPathDown(node.left));
		int right = Math.max(0, maxPathDown(node.right));
		maxValue = Math.max(maxValue, left + right + node.val);
		return Math.max(left, right) + node.val;
	}

}