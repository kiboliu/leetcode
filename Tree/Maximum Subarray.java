//Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

//For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
//the contiguous subarray [4,-1,2,1] has the largest sum = 6.

//DP, dp[i]代表以nums[i]结尾的最大maxValue
class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i < len; i++){
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}

//Another method:Kadane’s Algorithm 
//Algorithm that operates on arrays: 
//it starts at the left end (element A[1]) and scans through to the right end (element A[n])
//keeping track of the maximum sum subvector seen so far. 
//The maximum is initially A[0]. Suppose we've solved the problem for A[1 .. i - 1]; 
//how can we extend that to A[1 .. i]? 
//The maximum sum in the first I elements is either the maximum sum in the first i - 1 elements (which we'll call MaxSoFar),
//or it is that of a subvector that ends in position i (which we'll call MaxEndingHere).
//MaxEndingHere is either A[i] plus the previous MaxEndingHere, or just A[i], whichever is larger.
public static int maxSubArray(int[] A) {
    int maxSoFar=A[0], maxEndingHere=A[0];
    for (int i=1;i<A.length;++i){
    	maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
    	maxSoFar=Math.max(maxSoFar, maxEndingHere);	
    }
    return maxSoFar;
}

//Divide and Conquer: O(nlog(n))
public int maxSubArray(int[] nums){
	int l = 0;
	int h = nums.length - 1;
	return maxSubArraySum(nums, l, h);
}
public int maxSubArraySum(int[] nums, int l, int h) {
	if (l == h) return nums[l];
	int m = (l + h)/2;
	return Math.max(Math.max(maxSubArraySum(nums,l,m), maxSubArraySum(nums,m+1,h)),maxCrossingSum(nums,l,m,h));
}
public int maxCrossingSum(int[] nums, int l, int m, int h) { //从mid开始往左找到一个点使连续的sum最大，往右同理
	int sum = 0;
	int left_sum = Integer.MIN_VALUE;
	for (int i = m; i >= 1; i--) {
		sum = sum + nums[i];
		if (sum > left_sum) left_sum = sum;
	}
	sum = 0;
	int right_sum = Integer.MIN_VALUE;
	for (int i = m + 1; i <= h; i++) {
		sum = sum + nums[i];
		if (sum > right_sum) right_sum = sum;
	}
	return left_sum + right_sum;
}