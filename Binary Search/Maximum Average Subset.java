/*
给出一个整数数组，有正有负。找到这样一个子数组，他的长度大于等于 k，且平均值最大。

 注意事项

保证数组的大小 >= k
*/

//这个子数组的大小，一定在该数组的最大值和最小值之间。于是不断确定子数组最大平均数的范围，直到r=l
public class Solution {
    /**
     * @param nums an array with positive and negative numbers
     * @param k an integer
     * @return the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        double l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < l)
                l = nums[i];
            if (nums[i] > r)
                r = nums[i];
        }
        
        while (r - l >= 1e-6) {

            double mid = (l + r) / 2.0;

            if (check_valid(nums, mid, k)) {
                l = mid;
            }
            else {
                r = mid;
            }
        }

        return l;
    }
    //判断至少k个数的子数组的平均值与mid的大小。check_valid函数中不断计算每一个值与mid的差，并依次累加存储在sum[]中。
    //当大于等于k时，开始比较当前偏差之和与k个元素之前的所有偏差的最小值。如果当前偏差大于最小值，说明子数组的平均值比mid大，所以返回true，此时low = mid
    //如果遍历到最后都发现偏差的和始终小于最小值，说明所有满足条件的子数组的平均值都小于mid，返回false， high = mid
    private boolean check_valid(int[] nums, double mid, int k) {
        int n = nums.length;
        double min_pre = 0;
        double[] sum = new double[n + 1];
        sum[0] = 0; 
        for (int i = 1; i <= n; ++i) {
            sum[i] = sum[i - 1] + nums[i - 1] - mid;
            if (i >= k && sum[i] - min_pre >= 0) {
                return true;
            }
            if (i >= k)
                min_pre = Math.min(min_pre, sum[i - k + 1]);
        }
        return false;
    }
}