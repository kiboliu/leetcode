/*
 *Given a set of distinct integers, nums, return all possible subsets (the power set).
 *Note: The solution set must not contain duplicate subsets.
 *For example,
 *If nums = [1,2,3], a solution is:

 *[
 * [3],
 *  [1],
 *  [2],
 *  [1,2,3],
 *  [1,3],
 *  [2,3],
 *  [1,2],
 *  []
 *]
 */

//Backtrack. 
//For backtrack, the format is :
/*
Pick a starting point.
while(Problem is not solved)
    For each path from the starting point.
        check if selected path is safe, if yes select it
        and make recursive call to rest of the problem
        before which undo the current move.
    End For
If none of the move works out, return false, NO SOLUTON.
*/
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> tmp, int[] nums, int start) {
        res.add(new ArrayList<>(tmp)); //tmp is changable all the time, so it is necessary to clone it before storing it.
        for (int i = start; i < nums.length; i++) { //when i is still less than nums.length, the elements can be added to the tmp.
            tmp.add(nums[i]);
            backtrack(res, tmp, nums, i + 1);
            tmp.remove(tmp.size() - 1);//for the same level, remove the element just added, so another element can be added for enumeration.
        }
    }
}

//Process analysis.
/*
1. First, add "[]" to the res.
2. Next, the first-level loop, each element is added to the tmp one by one. For each one will be removed after added, so we can get [1], [2], [3] in this level.
3. Next, the second-level loop. For [1], we can get [1,2] and [1,3]; for [2], we can get [2,3]; for [3], its process ends, because 3 is nums[2], there is no more elements after it.
4. Next, the third-level loop. For [1,2], we can get [1,2,3]; for [1,3] and [2,3], we can not increase their elements any more.
*/


//Another method, use bit.
//0 stands for there is not a digit and 1 stands for there is a digit.
//So the result is 
/*
	0 0 0 -> []
	0 0 1 -> [1]
	0 1 0 -> [2]
	1 0 0 -> [3]
	0 1 1 -> [1,2]
	1 0 1 -> [1,3]
	1 1 0 -> [2,3]
	1 1 1 -> [1,2,3]
*/
public List<List<Integer>> subsets(int[] S) {
	Arrays.sort(S);
	int totalNumber = 1 << S.length; // the number of sublists.
	List<List<Integer>> collection = new ArrayList<List<Integer>>(totalNumber);
	for (int i=0; i<totalNumber; i++) {
		List<Integer> set = new LinkedList<Integer>();
		for (int j=0; j<S.length; j++) {
			if ((i & (1<<j)) != 0) { //add the element while the related bit is not 0
				set.add(S[j]);
			}
		}
		collection.add(set);
	}
	return collection;
}