//Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//The replacement must be in-place, do not allocate extra memory.

//分析：
//对于一数组，Permutation最大值是该数组呈降序排列。因此，对一个乱序数组而言
//先找到干扰降序sublist的第一个数（从右往左找），找到后将其改变。
//改变时为使得增量最小，应找到刚刚降序list中刚刚大于这个数的数，将两者交换
//交换后将sublist从低位到高位（从右往左）按降序排列。（实际上reverse就好了）
class Solution {
    public void nextPermutation(int[] nums) {
        //找到干扰降序的那个数
        int i = nums.length - 2;
        for(; i > 0 && nums[i] >= nums[i + 1]; i--)
            ;
        //如果这个数在第一个，代表该序列完全降序，于是将其reverse
        if(i == 0 && nums[i] >= nums[i + 1]){
            int j = 0;
            int k = nums.length - 1;
            while(k > 2*j){
                int temp = nums[j];
                nums[j] = nums[k - j];
                nums[k - j] = temp;
                j++;
            }
        //一般情况
        }else if(i >= 0){
            int j = i + 1;
            for(; j < nums.length&&nums[i] < nums[j]; j++)
                ;
            exchange(nums,i,j-1); //交换i和sublist中第一个大于i的数
            i++;
            int k = nums.length - 1;
            for(; i < k; i++, k--)
                exchange(nums,i,k);
            }//将i右边剩下的数reverse
    }
    private static void exchange(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}