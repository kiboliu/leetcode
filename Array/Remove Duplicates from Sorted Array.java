//Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
//Do not allocate extra space for another array, you must do this in place with constant memory.
//For example, Given input array nums = [1,1,2],
//Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

class Solution {
    public int removeDuplicates(int[] A) {
        if (A.length==0) return 0;
        int j=0;
        for (int i=0; i<A.length; i++)
            if (A[i]!=A[j]) A[++j]=A[i];
        return ++j;
    }
}

//What if duplicates are allowed at most twice?

class Solution{
    public int removeDuplicates(int[] nums){
        int n = nums.length;
        if(n <= 2) return n;
        int index = 2;
        for(int i = 2; i < n; i++){
            if(nums[i] != nums[index - 2]) //index指示更新后array每个元素的位置，相比之前多了index这个指示
                nums[index++] = nums[i];
        }
        return index;
    }
}