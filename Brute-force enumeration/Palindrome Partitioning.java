/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
*/

//从头开始找，如果是前一段是palidrome就会继续往下针对剩下的部分，进行partition然后看是否有palidromes。
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(res, new ArrayList<>(), s, 0);
        return res;
    }
    private void dfs(List<List<String>> res, List<String> tmp, String s, int start) {
        if(start == s.length()) {
            res.add(new ArrayList(tmp));
        } else {
            for (int i = start; i < s.length(); i++) {
                if(isPalindrome(s, start, i)){
                    tmp.add(s.substring(start, i + 1));
                    dfs(res, tmp, s, i + 1);
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }
    
    private boolean isPalindrome(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}