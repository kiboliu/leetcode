//Given an unsorted integer array, find the first missing positive integer.

//For example,
//Given [1,2,0] return 3,
//and [3,4,-1,1] return 2.

//Your algorithm should run in O(n) time and uses constant space.

//首先，需要弄清楚的是，对于k个正数而言，the first missing positive number一定是在[1,k+1]
//所以，首先排除数列中的0和负数，将正数单独放在一边；
//然后，用nums[i]来表示i位置的数是否存在，令nums[i]的值等于m，则m表明[1,k]中的m这个数字是存在的，因此将对应m-1位置的nums标记为负数，表示m存在
//nums[i]为负数表明在[1,k]中的i这个数是存在的，再搜一次找到为正数即[1,k]中不存在的i
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        if (n == 0) return 1;
        int k = partition(nums) + 1;
        int temp = 0;
        int first_missing_Index = k;
        for (int i = 0; i < k; i++) {
            temp = Math.abs(nums[i]);
            if (temp <= k) {
                nums[temp - 1] = nums[temp - 1] < 0 ? nums[temp - 1] : -nums[temp - 1];
            }
        }
        for(int i = 0; i < k; i++) {
            if (nums[i] > 0) {
                first_missing_Index = i;
                break;
            }
        }
        return first_missing_Index + 1;
    }
    public int partition(int[] nums) {
        int n = nums.length;
        int q = -1;
        for (int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                q++;
                swap(nums, q, i);
            }
        }
        return q;
    }
    //不用extra space的交换两个数字的方式，很厉害了
    public void swap(int[] nums, int i, int j) {
        if(i != j) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}