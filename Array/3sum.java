//Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//Note: The solution set must not contain duplicate triplets.

//本题中0可以换成任意target值，方法可以推广到k-sum，先排序(O(nlogn))，然后做k-2次循环
//在最内层循环左右夹逼，时间复杂度O(max{nlogn,n^(k-1)})

class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num.length < 3) return res;
        for(int i = 0; i < num.length - 2; i++){
            int j = i + 1;
            if(i >= 1 && num[i] == num[i - 1]) continue;
            int k = num.length - 1;
            while(j < k){
                if(num[i] + num[j] + num[k] < 0){
                    j++;
                    while(num[j] == num[j-1] && j < k) j++;
                }else if(num[i] + num[j] + num[k] > 0){
                    k--;
                    while(num[k] == num[k+1] && j < k) k--;
                }else{
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(num[i]);
                    temp.add(num[j]);
                    temp.add(num[k]);
                    res.add(temp);
                    j++;
                    k--;
                    while(num[j] == num[j-1] && num[k] == num[k+1] && j < k) j++;
                }
            }
        }
        return res;
    }
}