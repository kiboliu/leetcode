//Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
//For example,
//Given n = 3, there are a total of 5 unique BST's.

//动态规划，以每个数为root形成的树的个数 = numTrees(以左边的个数的值为root形成的树) * numTrees(以右边的个数的值为root形成的树) 
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int k = 1; k <= i; k++){
                dp[i] += dp[k - 1] * dp[i - k];
            }
        }
        return dp[n];
    }
}