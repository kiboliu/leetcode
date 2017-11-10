/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

For example, given s = "aab",
Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

//DP, 牛逼版, O(n2) time & O(n) space.
class Solution {
      public int minCut(String s) {
        if(s.length()==0)return 0;
        int[]c=new int[s.length()+1];
        for(int i=0;i<s.length();i++)c[i]=Integer.MAX_VALUE;
        c[s.length()]=-1;
        for(int i=s.length()-1;i>=0;i--){
            for(int a=i,b=i;a>=0 && b<s.length() && s.charAt(a)==s.charAt(b);a--,b++) c[a]=Math.min(c[a],1+c[b+1]);
            for(int a=i,b=i+1;a>=0 && b<s.length() && s.charAt(a)==s.charAt(b);a--,b++) c[a]=Math.min(c[a],1+c[b+1]);
        }
        return c[0];
    }
}

//DP 正常版
class Solution {
	public int minCut(String s) {
		char[] c = s.toCharArray();
		int n = c.length;
		int[] cut = new int[n];
		boolean[][] pal = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = 0; j <= i; j++) {
				if (c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
					pal[j][i] = true;
					min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
				}
			}
			cut[i] = min;
		}
		return cut[n - 1];
	}
}