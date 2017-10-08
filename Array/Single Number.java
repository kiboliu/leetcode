//Given an array of integers, every element appears twice except for one. Find that single one.

//异或运算, 0^N = N, N^N = 0.
public int singleNumber(int[] nums) {
    int ans =0;
    
    int len = nums.length;
    for(int i=0;i!=len;i++)
        ans ^= nums[i];
    
    return ans;
    
}

//If every element appears three times except for one.
public int singleNumber(int[] A) {
    int ones = 0, twos = 0;
    for(int i = 0; i < A.length; i++){
        ones = (ones ^ A[i]) & ~twos;
        twos = (twos ^ A[i]) & ~ones;
    }
    return ones;
}

//Java的位运算
//1.<< 左移，正数／负数都补全0； 2.>> 右移，正数补全0，负数补全1； 
//3. >>> 无符号右移，负数此时会补全0； 4. & 位与 1&1=1，其余为0
//5. | 位或 0|0=0，其余为0； 6. ^ 位异或 1^1 and 0^0 = 0, 1^0 and 0^1 = 1；
//7. ~ 位非