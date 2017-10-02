//Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
//Find all unique quadruplets in the array which gives the sum of target.
//The solution set must not contain duplicate quadruplets.

//方法1， 先排序后夹逼，时间O(n3)，空间O(1)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new LinkedList();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){
            for(int j=nums.length-1;j>=3;j--){          
                int lo=i+1,hi=j-1;
                while(lo<hi){
                    if(nums[i]+nums[lo]+nums[hi]+nums[j]==target){
                        res.add(Arrays.asList(nums[i],nums[lo],nums[hi],nums[j]));
                        lo++;
                        hi--;
                    }else if(nums[i]+nums[lo]+nums[hi]+nums[j]<target){
                        lo++;
                    }else{
                        hi--;
                    }
                }
            }   
        }
        //去重可以放在之前。
        for(int i=0;i<res.size();i++){
            for(int j=res.size()-1;j>i;j--){
                if(res.get(j).equals(res.get(i))){
                    res.remove(j);
                }
            }
        }
        return res;
    }
}

//方法2，用hashmap先缓存两个数的和，时间平均O(n2)，最坏O(n4)，空间O(n2), 其实慢了ORZ...
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length < 4) return res;
        Arrays.sort(nums);
        HashMap<Integer,List<Key>> cach = new HashMap<Integer,List<Key>>();
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                Key temp = new Key(i,j);
                if(cach.get(nums[i]+nums[j]) != null) {
                        cach.get(nums[i]+nums[j]).add(temp);
                }else {
                        List<Key> newone = new ArrayList<Key>();
                        newone.add(temp);
                        cach.put(nums[i]+nums[j], newone);
                }
            }
        }
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                int key = target - nums[i] - nums[j];
                if(cach.get(key) == null) continue;
                List<Key> temp = cach.get(key);
                for(int k = 0; k < temp.size(); k++) {
                    if(i == temp.get(k).x) continue;
                    if(i == temp.get(k).y) continue;
                    if(j == temp.get(k).x) continue;
                    if(j == temp.get(k).y) continue;
                    res.add(Arrays.asList(nums[i],nums[j],nums[temp.get(k).x],nums[temp.get(k).y]));
                }
            }
        }
        
        for(int i = 0; i < res.size(); i++){
            for(int j = res.size() - 1; j > i; j--){
                    List<Integer> temp1 = res.get(i);
                    List<Integer> temp2 = res.get(j);
                    Collections.sort(temp1);
                    Collections.sort(temp2);
                if(temp1.equals(temp2)){
                    res.remove(j);
                }
            }
        }
        return res;
    }
    class Key {

        private final int x;
        private final int y;

        public Key(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key = (Key) o;
            return x == key.x && y == key.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
