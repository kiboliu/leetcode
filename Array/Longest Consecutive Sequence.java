//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//For example,
//Given [100, 4, 200, 1, 3, 2],
//The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
//Your algorithm should run in O(n) complexity.

//如果允许O(nlogn)的复杂度，则先排序。
class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        binarysort(left, right, nums);
        int count = 0;
        List<Integer> total = new ArrayList<Integer>();
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == (nums[i - 1] + 1)) count++;
            else if(nums[i] == nums[i - 1]){
                continue;
            }else{
                total.add(++count);
                count = 0;
            }
        }
        total.add(++count);
        int max = total.get(0);
        for(int i = 0; i < total.size(); i++) {
        	if(total.get(i) > max) max = total.get(i);
        }
        return max;
        
    }
    public static void binarysort(int left, int right, int array[]) {
        int i, j, temp;
        i = left;
        j = right;
        int middle = array[(left + right) / 2];
        do {
            while (i < right && array[i] < middle) {
                i++;
            }
            while (j > left && array[j] > middle) {
                j--;
            }
            if (i <= j) {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);
        if (j > left) {
            binarysort(left, j, array);
        }
        if (i < right) {
            binarysort(i, right, array);
        }
    }
}

//若是O(n),使用HashMap
public int longestConsecutive(int[] num) {
    int res = 0;
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int n : num) {
        if (!map.containsKey(n)) {
            int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;
            int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;
            // sum: length of the sequence n is in
            int sum = left + right + 1;
            map.put(n, sum);
            
            // keep track of the max length 
            res = Math.max(res, sum);
            
            // extend the length to the boundary(s)
            // of the sequence
            // will do nothing if n has no neighbors
            map.put(n - left, sum);
            map.put(n + right, sum);
        }
        else {
            // duplicates
            continue;
        }
    }
    return res;
}