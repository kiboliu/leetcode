//Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

// First Method. 判断当前有没有回文串，如果有的话就增加，并且增大currlength以确定断开后出现的回文串是比之前大的才能被替代。
public String longestPalindrome(String s) {
    String res = "";
    int currLength = 0;
    for(int i=0;i<s.length();i++){
        if(isPalindrome(s,i-currLength-1,i)){
            res = s.substring(i-currLength-1,i+1);
            currLength = currLength+2;
        }
        else if(isPalindrome(s,i-currLength,i)){
            res = s.substring(i-currLength,i+1);
            currLength = currLength+1;
        }
    }
    return res;
}
public boolean isPalindrome(String s, int begin, int end){
    if(begin<0) return false;
    while(begin<end){
    	if(s.charAt(begin++)!=s.charAt(end--)) return false;
    }
    return true;
}
// Second Method. 在每一点往左右走，判断以该点为中心的最长回文串长度并记录。
public class Solution {
	private int lo, maxLen;

	public String longestPalindrome(String s) {
		int len = s.length();
		if (len < 2)
			return s;
	
    	for (int i = 0; i < len-1; i++) {
     		extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
     		extendPalindrome(s, i, i+1); //assume even length.
    	}
    	return s.substring(lo, lo + maxLen);
	}

	private void extendPalindrome(String s, int j, int k) {
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}
		if (maxLen < k - j - 1) {
			lo = j + 1;
			maxLen = k - j - 1;
		}
	}
}

// Third Method, dynamic programming. dp(i,j) represents whether s(i ... j) can form a palindromic substring
// dp(i,j) is true when s(i) equals to s(j) and s(i+1 ... j-1) is a palindromic substring.
// When find a palindrom, check if it's the longest one.

public String longestPalindrome(String s) {
  int n = s.length();
  String res = null;
    
  boolean[][] dp = new boolean[n][n];
    
  for (int i = n - 1; i >= 0; i--) {
    for (int j = i; j < n; j++) {
      dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
            
      if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
        res = s.substring(i, j + 1);
      }
    }
  }
    
  return res;
}