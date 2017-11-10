/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

*/

//DFS. 先从左往右走，遇到多余的')'便停下来，然后在之前的string中找到第一个')'并remove即可，以防出现重复值；
//同时，需保留上一个remove的位置，使得不会重复remove
//从左往右结束后再从右往左走一次，即可结束。

class Solution {
	public List<String> removeInvalidParentheses (String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[]{'(', ')'});
		return ans;
	}
	public void remove (String s, List<String> ans, int last_i, int last_j, char[] par) {
		for (int stack = 0, i = last_i; i < s.length(); i++) {
			if (s.charAt(i) == par[0]) stack++;
			if (s.charAt(i) == par[1]) stack--;
			if (stack >= 0) continue;
			for (int j = last_j; j <= i; j++) {
				if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
					remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
				}
			}
			return;
		}
		String reversed = new StringBuilder(s).reverse().toString();
		if (par[0] == '(')
			remove(reversed, ans, 0, 0, new char[]{')', '('});
		else
			ans.add(reversed);
	}
}