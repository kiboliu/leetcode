//The set [1,2,3,…,n] contains a total of n! unique permutations.
//By listing and labeling all of the permutations in order,
//We get the following sequence (ie, for n = 3):
//"123"
//"132"
//"213"
//"231"
//"312"
//"321"
//Given n and k, return the kth permutation sequence.
//Note: Given n will be between 1 and 9 inclusive.

//自己做出来了耶开心！😄 每次挑出第一个位置的数后，剩下来的数还是按之前的规则来就好了！）
class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> num  = new ArrayList<Integer>();
        List<Integer> res  = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++){
            num.add(i);
        }
        kthPermutation(n, n, k, res, num);
        StringBuilder sb = new StringBuilder();
        for(Integer m : res) {
                sb.append(m);
        }
        return (sb.toString());
    }
    private void kthPermutation(int val, int n, int k, List<Integer> list, List<Integer> num){
        //当结果数满足要求时返回。
        if(list.size() == val) {
            return;
        }
        //求(n-1)!
        int seq = 1;
        for(int i = 1; i < n; i++){
            seq = seq * i;
        }
        //求出此次添加到结果的数字
        int res = (k - 1)/seq;
        //准备开始新的一轮
        k = k - ((k - 1) / seq) * seq;
        list.add(num.get(res));
        n--;
        //记得要把已经用过的数字去掉
        num.remove(res);
        kthPermutation(val, n, k, list, num);
    }
}